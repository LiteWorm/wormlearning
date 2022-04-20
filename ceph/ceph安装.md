## 添加hosts

```shell
vi /etc/hosts

#节点地址
10.*.*.104 master
10.*.*.110 node1
10.*.*.106 node2
10.*.*.107 node3
```



## 安装相关镜像地址

> 1、安装ceph仓库镜像地址

```shell
rpm -ivh http://mirrors.ustc.edu.cn/ceph/rpm-mimic/el7/noarch/ceph-release-1-1.el7.noarch.rpm
```

> 2、安装epel-release 仓库地址

```shell
rpm -ivh http://mirrors.ustc.edu.cn/epel/epel-release-latest-7.noarch.rpm
```

## 创建ceph管理账户

### 新建cephadmin账户

> 1、创建用户并初始化密码

```shell
useradd cephadmin  && echo 2014$test | passwd --stdin cephadmin
```

> 2、设置无密钥执行sudo权限

```shell
echo "cephadmin ALL = (root) NOPASSWD:ALL" | sudo tee /etc/sudoers.d/cephadmin
chmod 0440 /etc/sudoers.d/cephadmin
```

### 设置免密登录

1、生成密钥

```shell
 ssh-keygen -t rsa -P ''
```

> 各个节点均需要执行此操作

2、开启密钥认证

```shell
vi /etc/ssh/sshd_config

#启用公钥认证
PubkeyAuthentication yes
AuthorizedKeysFile      .ssh/authorized_keys


```

> 配置完成后需在各个节点执行重新sshd
>
> ```shell
> systemctl restart sshd
> ```
>
> 

3、将各节点的密钥复制到主master节点

```shell
 scp ~/.ssh/id_rsa.pub  cephadmin@master:~/.ssh/node1.id_rsa.pub
 scp ~/.ssh/id_rsa.pub  cephadmin@master:~/.ssh/node2.id_rsa.pub
 scp ~/.ssh/id_rsa.pub  cephadmin@master:~/.ssh/node3.id_rsa.pub
```

4、在master节点生成authorized_keys 文件

```shell
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
cat ~/.ssh/node1.id_rsa.pub >> ~/.ssh/authorized_keys
cat ~/.ssh/node2.id_rsa.pub >> ~/.ssh/authorized_keys
cat ~/.ssh/node3.id_rsa.pub >> ~/.ssh/authorized_keys
```

5、将生成的authorized_keys文件下发到各个节点

```shell
scp ~/.ssh/authorized_keys cephadmin@node1:~/.ssh
scp ~/.ssh/authorized_keys cephadmin@node2:~/.ssh
scp ~/.ssh/authorized_keys cephadmin@node3:~/.ssh
```

6、在各个节点设置认证目录权限

```shell
chmod 700 ~/.ssh
chmod 600 ~/.ssh/authorized_keys
```

## 在管理节点安装ceph-deploy

```shell
yum -y update 
yum install ceph-deploy python-setuptools python2-subprocess32
```

## 部署RADOS存储集群

### 初始化RADOS集群

1、在管理节点上以cephadmin用户创建集群相关的配置文件目录：

```shell
mkdir ceph-cluster
cd ceph-cluster
```

2、初始化第一个MON节点，准备创建集群：

初始化呢第一个MON节点的命令格式为``ceph-deploy new {inital-monitor-node(s)}``

```shell
ceph-deploy new --public-network=10.*.*.0/8 --private-network=10.*.*.0/8 node1
```

> ```shell
> [cephadmin@master ceph-cluster]$ ceph-deploy new --help
> usage: ceph-deploy new [-h] [--no-ssh-copykey] [--fsid FSID]
>                        [--cluster-network CLUSTER_NETWORK]
>                        [--public-network PUBLIC_NETWORK]
>                        MON [MON ...]
> 
> Start deploying a new cluster, and write a CLUSTER.conf and keyring for it.
> 
> positional arguments:
>   MON                   initial monitor hostname, fqdn, or hostname:fqdn pair
> 
> optional arguments:
>   -h, --help            show this help message and exit
>   --no-ssh-copykey      do not attempt to copy SSH keys
>   --fsid FSID           provide an alternate FSID for ceph.conf generation
>   --cluster-network CLUSTER_NETWORK
>                         specify the (internal) cluster network
>   --public-network PUBLIC_NETWORK
>                         specify the public network for a cluster
> ```

生成节点信息

```shell
ceph.conf     ceph主配置文件
ceph-deploy-ceph.log   部署日志
ceph.mon.keyring       mon通信是使用的内部通信环密钥
```

```shell
[cephadmin@master ceph-cluster]$ cat ceph.conf 
[global]
fsid = 034ac1f4-a49c-4489-854b-e9432e236bf0
public_network = 10.*.*.0/8
cluster_network = 10.*.*.0/8
mon_initial_members = node1
mon_host = 10.*.*.110
auth_cluster_required = cephx
auth_service_required = cephx
auth_client_required = cephx
```

3、安装ceph集群

> ceph-deploy 命令能够以远程方式连入ceph集群个节点完成程序包安装等操作，命令格式如下：
>
> ```shell
> ceph-deploy install {ceph-node} [{ceph-node} ...]
> ```

```shell
ceph-deploy install node1 node2 node3 master 
```

> 也可以在每个节点上分别执行 
>
> ```shell
> sudo yum -y  install ceph ceph-radosgw
> ```
>
> 执行完成之后在管理节点上执行
>
> ```shell
>  ceph-deploy install --no-adjust-repos node1 node2 node3 master
> ```

4、配置初始MON节点，并收集所有密钥：

```shell
ceph-deploy mon creae-install
```

> ```shell
> cephadmin@master ~]$ ceph-deploy  mon --help
> usage: ceph-deploy mon [-h] {add,create,create-initial,destroy} ...
> 
> Ceph MON Daemon management
> 
> positional arguments:
>   {add,create,create-initial,destroy}
>     add                 Add a monitor to an existing cluster:
>                                 ceph-deploy mon add node1
>                         Or:
>                                 ceph-deploy mon add --address 192.168.1.10 node1
>                         If the section for the monitor exists and defines a `mon addr` that
>                         will be used, otherwise it will fallback by resolving the hostname to an
>                         IP. If `--address` is used it will override all other options.
>     create              Deploy monitors by specifying them like:
>                                 ceph-deploy mon create node1 node2 node3
>                         If no hosts are passed it will default to use the
>                         `mon initial members` defined in the configuration.
>     create-initial      Will deploy for monitors defined in `mon initial
>                         members`, wait until they form quorum and then
>                         gatherkeys, reporting the monitor status along the
>                         process. If monitors don't form quorum the command
>                         will eventually time out.
>     destroy             Completely remove Ceph MON from remote host(s)
> 
> optional arguments:
>   -h, --help            show this help message and exit
> ```

```shell
ceph.bootstrap-mds.keyring  
ceph.bootstrap-osd.keyring  
ceph.client.admin.keyring   --- 客户端管理密钥
ceph.bootstrap-mgr.keyring 
ceph.bootstrap-rgw.keyring  
```

5、把配置文件和admin密钥拷贝到ceph集群各个节点，以免每次执行`ceph` 命令式不得不指定MON节点地址和ceph.client.admin.king

```shell
ceph-deploy admin node1 node2 node3 master
```

>```shell
>[cephadmin@node3 ~]$ ls -l /etc/ceph
>总用量 12
>-rw------- 1 root root 151 4月  21 15:56 ceph.client.admin.keyring
>-rw-r--r-- 1 root root 253 4月  21 15:56 ceph.conf
>-rw-r--r-- 1 root root  92 4月  24 2020 rbdmap
>-rw------- 1 root root   0 4月  21 15:56 tmptbn6BW
>```

 6、然后在ceph集群冲需要运行ceph命令的节点上（或所有几点上）以root用户的身份设定用户cephadmin能够读取到/etc/ceph/ceph.client.admin.keyring文件：

```shell
sudo setfacl -m u:cephadmin:rw /etc/ceph/ceph.client.admin.keyring
```

7、配置Manager节点，启动 ceph-mgr集成：

```shell
ceph-deploy mgr create master
```

> ```shell
> [cephadmin@master ceph-cluster]$ ceph -s
>   cluster:
>     id:     034ac1f4-a49c-4489-854b-e9432e236bf0
>     health: HEALTH_WARN
>             OSD count 0 < osd_pool_default_size 3
>  
>   services:
>     mon: 1 daemons, quorum node1
>     mgr: master(active)
>     osd: 0 osds: 0 up, 0 in
>  
>   data:
>     pools:   0 pools, 0 pgs
>     objects: 0  objects, 0 B
>     usage:   0 B used, 0 B / 0 B avail
>     pgs:     
> ```

## 向RADOS集群添加OSD

### 列出并擦净磁盘

1、``ceph-deploy disk`` 命令可以列出并检查出OSD节点所有可用的磁盘的相关信息：

```shell
ceph-deploy disk list master node1 node2 node3
```

2、在管理节点上使用ceph-deploy 命令擦除计划用于OSD磁盘上所有分区表和数据一遍用于OSD，命令格式为``ceph-deploy disk zap {osd-server-name} {disk-name}``，需要注意的是此步会清除目标设备上的所有数据。

```shell
ceph-deploy disk zap node1 /dev/sdb
ceph-deploy disk zap node1 /dev/sdc
ceph-deploy disk zap node2 /dev/sdb
ceph-deploy disk zap node2 /dev/sdc
ceph-deploy disk zap node3 /dev/sdb
ceph-deploy disk zap node3 /dev/sdc
ceph-deploy disk zap master /dev/sdb
ceph-deploy disk zap master /dev/sdc
```

>```shell
>[cephadmin@master ceph-cluster]$ ceph-deploy disk zap --help
>usage: ceph-deploy disk zap [-h] [--debug] [HOST] DISK [DISK ...]
>
>positional arguments:
>  HOST        Remote HOST(s) to connect
>  DISK        Disk(s) to zap
>
>optional arguments:
>  -h, --help  show this help message and exit
>  --debug     Enable debug mode on remote ceph-volume calls
>```

> Tips：若设备上此前有数据，则可能需要在响应节点上以root用户使用``ceph-deploy lvm zap --destory {DEVICE}`` 命令进行；

### 添加OSD

1、命令为``ceph-deploy osd create {node} --data {data-disk}``

> ```shell
> [cephadmin@master ceph-cluster]$ ceph-deploy osd --help
> usage: ceph-deploy osd [-h] {list,create} ...
> 
> Create OSDs from a data disk on a remote host:
> 
>     ceph-deploy osd create {node} --data /path/to/device
> 
> For bluestore, optional devices can be used::
> 
> 	#元数据的存储位置 --block-db 
>     ceph-deploy osd create {node} --data /path/to/data --block-db /path/to/db-device
>     #数据库日志的存储位置 --block-wal 
>     ceph-deploy osd create {node} --data /path/to/data --block-wal /path/to/wal-device
>     ceph-deploy osd create {node} --data /path/to/data --block-db /path/to/db-device --block-wal /path/to/wal-device
> 
> For filestore, the journal must be specified, as well as the objectstore::
> 
>     ceph-deploy osd create {node} --filestore --data /path/to/data --journal /path/to/journal
> 
> For data devices, it can be an existing logical volume in the format of:
> vg/lv, or a device. For other OSD components like wal, db, and journal, it
> can be logical volume (in vg/lv format) or it must be a GPT partition.
> 
> positional arguments:
>   {list,create}
>     list         List OSD info from remote host(s)
>     create       Create new Ceph OSD daemon by preparing and activating a
>                  device
> 
> optional arguments:
>   -h, --help     show this help message and exit
> ```
>
> ```shell
> [cephadmin@master ceph-cluster]$ ceph-deploy osd create --help
> usage: ceph-deploy osd create [-h] [--data DATA] [--journal JOURNAL]
>                               [--zap-disk] [--fs-type FS_TYPE] [--dmcrypt]
>                               [--dmcrypt-key-dir KEYDIR] [--filestore]
>                               [--bluestore] [--block-db BLOCK_DB]
>                               [--block-wal BLOCK_WAL] [--debug]
>                               [HOST]
> 
> positional arguments:
>   HOST                  Remote host to connect
> 
> optional arguments:
>   -h, --help            show this help message and exit
>   --data DATA           The OSD data logical volume (vg/lv) or absolute path
>                         to device
>   --journal JOURNAL     Logical Volume (vg/lv) or path to GPT partition
>   --zap-disk            DEPRECATED - cannot zap when creating an OSD
>   --fs-type FS_TYPE     filesystem to use to format DEVICE (xfs, btrfs)
>   --dmcrypt             use dm-crypt on DEVICE
>   --dmcrypt-key-dir KEYDIR
>                         directory where dm-crypt keys are stored
>   --filestore           filestore objectstore
>   --bluestore           bluestore objectstore
>   --block-db BLOCK_DB   bluestore block.db path
>   --block-wal BLOCK_WAL
>                         bluestore block.wal path
>   --debug               Enable debug mode on remote ceph-volume calls
> ```
>
> 

```shell
ceph-deploy osd create  --data /dev/sdb node1
ceph-deploy osd create  --data /dev/sdb node2
ceph-deploy osd create  --data /dev/sdb node3
ceph-deploy osd create  --data /dev/sdc node1
ceph-deploy osd create  --data /dev/sdc node2
ceph-deploy osd create  --data /dev/sdc node3
ceph-deploy osd create  --data /dev/sdb master
ceph-deploy osd create  --data /dev/sdc master
```

```ceph
[cephadmin@node3 ~]$ ceph -s
  cluster:
    id:     034ac1f4-a49c-4489-854b-e9432e236bf0
    health: HEALTH_OK
 
  services:
    mon: 1 daemons, quorum node1
    mgr: master(active)
    osd: 8 osds: 8 up, 8 in
 
  data:
    pools:   0 pools, 0 pgs
    objects: 0  objects, 0 B
    usage:   8.0 GiB used, 792 GiB / 800 GiB avail
    pgs:     
```

## 测试上传/下载数据对象

1、存储数据是，客户端必须首先来截至RADOS集群上的摸个存储池，然后根据对象名称由相关的CRUSH规则完成数据对象寻址。于是，为了测试集群的数据存取功能，这里首先创建一个用于测试的存储池mypool，并设定器PG数量为16个

```shell
ceph osd pool create mypool 16 
rados ls --pool=mypool 
```

2、 上传测试文件到存储池中，例如下面的 ``rados put`` 命令

```shell
[cephadmin@master ~]$ rados put ceph-deploy-ceph.log /home/cephadmin/ceph-deploy-ceph.log -p mypool 
[cephadmin@master ~]$ rados ls -p mypool 
ceph-deploy-ceph.log
```

3、 使用``ceph osd map mypool ceph-deploy-ceph.log``命令可以获取存储池中对象的位置

>osd map <poolname> <objectname>     find pg for <object> in <pool> 
> {<nspace>}                          with [namespace]

```shell
[cephadmin@master ~]$ ceph osd map mypool ceph-deploy-ceph.log
osdmap e36 pool 'mypool' (1) object 'ceph-deploy-ceph.log' -> pg 1.839efb5a (1.a) -> up ([5,4,7], p5) acting ([5,4,7], p5)
```

4、删除数据对象， 使用``rados rm``命令

```shell
[cephadmin@master ~]$ rados rm ceph-deploy-ceph.log --pool=mypool
```

5、删除存储池，删除存储池命令存在风险，Ceph默认进制此类操作。管理员需要在ceph.conf中启用支持删除存储池的操作后，房卡使用类似如下的命令删除存储池。

```shell
ceph osd pool rm mypool mypool --yes-i-really-mean-it
```

## 扩展ceph集群

### 扩展监视器节点

1、Ceph存储集群需要至少运行一个Ceph Monitor 和一个 Ceph Manager，生产环境中，为了实现高可用性，Ceph集群通常运行多个监视器，以免单监视器故障导致整个存储集群崩溃。Ceph使用Pasox算法，该算法需要半数以上的监视器（大于n/2，其中n为监视器数量）才能形成法定人数。尽管非必须，但奇数个监视器往往更好。

> ``ceph-deploy mon add {ceph-node}`` 命令可以一次添加一个监视器节点到集群中。

```shell
ceph-deploy mon add node2 
ceph-deploy mon add node3
```

2、设置完成后，可以在ceph客户端上查看监视器及法定人数的相关状态

```shell
[cephadmin@master ceph-cluster]$ ceph quorum_status --format json-pretty

{
    "election_epoch": 14,
    "quorum": [
        0,
        1,
        2
    ],
    "quorum_names": [
        "node2",
        "node3",
        "node1"
    ],
    "quorum_leader_name": "node2",
    "monmap": {
        "epoch": 3,
        "fsid": "034ac1f4-a49c-4489-854b-e9432e236bf0",
        "modified": "2021-04-22 11:18:03.088243",
        "created": "2021-04-21 13:33:44.505698",
        "features": {
            "persistent": [
                "kraken",
                "luminous",
                "mimic",
                "osdmap-prune"
            ],
            "optional": []
        },
        "mons": [
            {
                "rank": 0,
                "name": "node2",
                "addr": "10.*.*.106:6789/0",
                "public_addr": "10.*.*.106:6789/0"
            },
            {
                "rank": 1,
                "name": "node3",
                "addr": "10.*.*.107:6789/0",
                "public_addr": "10.*.*.107:6789/0"
            },
            {
                "rank": 2,
                "name": "node1",
                "addr": "10.*.*.110:6789/0",
                "public_addr": "10.*.*.110:6789/0"
            }
        ]
    }
}
```

### 扩展Manager节点

1、Ceph Manager 守护进程以“Active/Standby” 模式运行，部署其它ceph-mgr守护程序可确保在Active节点或其上的ceph-mgr守护进程故障时，其中的一个Standby示例可以在不中断服务的情况下接管其任务。

> ``ceph-deploy mgr create {new-mamager-nodes}`` 命令可以 一次添加多个Manager节点

``` shell
ceph-deploy mgr create node1
```

2、 添加完成后，查看 ``ceph -s``：

```shell
[cephadmin@master ceph-cluster]$ ceph -s 
  cluster:
    id:     034ac1f4-a49c-4489-854b-e9432e236bf0
    health: HEALTH_WARN
            application not enabled on 1 pool(s)
            too few PGs per OSD (6 < min 30)
 
  services:
    mon: 3 daemons, quorum node2,node3,node1
    mgr: master(active), standbys: node1
    osd: 8 osds: 8 up, 8 in
 
  data:
    pools:   1 pools, 16 pgs
    objects: 1  objects, 15 KiB
    usage:   8.0 GiB used, 792 GiB / 800 GiB avail
    pgs:     16 active+clean
 
```

## Ceph对象网关

### 安装ceph对象网关

1、在管理节点工作目录下，给Ceph对象网关节点安装Ceph对象所需的软件包：

> ```shell
> ceph-deploy install --rgw <gateway-node1> [<gateway-node2> ...]
> ```

2、将Ceph对象网关节点设置为管理员节点

> ```shell
> ceph-deploy admin <gateway-node1>
> ```

### 新建网关实例

1、在管理节点的工作目录下，使用命令在Ceph对象网关节点上新建一个Ceph对象网关实例

> ```shell
> ceph-deploy rgw create <gateway-node1>
> ```

```shell
ceph-deploy rgw create node3
```

2、在网关服务成功运行后，使用未经授权的请求来访问端口7480

``` shell
curl http://node3:7480
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ListAllMyBucketsResult xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
    <Owner>
        <ID>anonymous</ID>
        <DisplayName></DisplayName>
    </Owner>
    <Buckets></Buckets>
</ListAllMyBucketsResult>
```

### 修改默认端口

1、在管理节点工作目录下的ceph.con中添加如下内容

```shell
[client.rgw.node3]
    rgw_host = node3
    rgw_frontends = "civetweb port=80"
```

2、将密钥和配置文件推送到ceph对象网关

```shell
ceph-deploy --overwrite-conf config push node3
```

3、在ceph对象网关节点中赋予ceph对配置文件和密钥的访问权限

```shell
sudo setfacl -m u:cephadmin:rw /etc/ceph/ceph.client.admin.keyring
```

4、将radosgw 加入到systemctl 启动管理

```shell
sudo systemctl enable ceph-radosgw@rgw.node3
```

5、重启ceph对象网关

```shell
sudo systemctl restart ceph-radosgw@rgw.node3
```

6、测试端口修改结果

```shell
[cephadmin@node3 ceph]$ curl http://node3
<?xml version="1.0" encoding="UTF-8"?>
<ListAllMyBucketsResult xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
<Owner>
  <ID>anonymous</ID>
  <DisplayName></DisplayName>
</Owner>
<Buckets></Buckets>
</ListAllMyBucketsResult>
```

## 使用网关

### 为S3访问创建RADOSGW用户

1、新建一个radosgw用户并分配权限，使用命令 radosgw-admin;可以在radosgw主机上执行 ``man radosgw-admin``查看详细使用说明

2、创建新用户，在gateway host上执行如下命令

```shell
sudo radosgw-admin user create --uid="testuser" --display-name="First Test User"
```

```shell
[cephadmin@node3 ceph]$ sudo radosgw-admin user create --uid="testuser" --display-name="First Test User"
{
    "user_id": "testuser",
    "display_name": "First Test User",
    "email": "",
    "suspended": 0,
    "max_buckets": 1000,
    "auid": 0,
    "subusers": [],
    "keys": [
        {
            "user": "testuser",
            "access_key": "F05J997PB65QJJ4H2UD7",
            "secret_key": "1VMXdaLKm7iBVAfjXU9nB2R2SxycPriuyR2RK1Wl"
        }
    ],
    "swift_keys": [],
    "caps": [],
    "op_mask": "read, write, delete",
    "default_placement": "",
    "placement_tags": [],
    "bucket_quota": {
        "enabled": false,
        "check_on_raw": false,
        "max_size": -1,
        "max_size_kb": 0,
        "max_objects": -1
    },
    "user_quota": {
        "enabled": false,
        "check_on_raw": false,
        "max_size": -1,
        "max_size_kb": 0,
        "max_objects": -1
    },
    "temp_url_keys": [],
    "type": "rgw",
    "mfa_ids": []
}
```

> Note

> 其中 ``keys->access_key``和``keys->secret_key``的值在访问的时候需要用来做验证

> Important

> 请检查输出的key。有时候``radosgw-damin``会在生成的JSON中的``access-key``和``secret_key``部分包含有转义字符``\``，并且一些客户端无法处理JSON中这个字符。
>
> 不就措施包括移除JSON中的``\``字符串，将该字符转封装到引号中，重新声场这个key并确保不再包含``\``,或手动指定``access_key``和``secret_key``。
>
> 如果``radosgw-admin``生成的JSON中的同一个key包含转义字符``\``同时还包含有正斜杠``/``，形如``\/``，请只移除JSON转义字符``\``，不要删除正斜杠``/``，因为在key中它是一个有效字符。

### 访问验证

新建python脚本

```python
import boto
import boto.s3.connection

access_key = 'F05J997PB65QJJ4H2UD7'
secret_key = '1VMXdaLKm7iBVAfjXU9nB2R2SxycPriuyR2RK1Wl'

conn = boto.connect_s3(
    aws_access_key_id= access_key,
    aws_secret_access_key=secret_key,
    host = '10.*.*.107',
    port = 80,
    is_secure = False,
    calling_format = boto.s3.connection.OrdinaryCallingFormat()
)

bucket = conn.create_bucket('my-new-bucket')

for bt in conn.get_all_buckets():
    print("name= ",
        bt.name,
          ",create= ",
        bt.creation_date
    )
```

运行脚本：

```shell
name=  my-new-bucket ,create=  2021-04-23T08:40:24.485Z
name=  my-new-bucket1 ,create=  2021-04-23T08:41:45.571Z
```

