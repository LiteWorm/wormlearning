#include <iostream>
#include <cstdio>
#include <unistd.h>
#include <pthread.h>
#include <sys/time.h>
#include <cstring>
using namespace std;

#define LEN (2*1024)
#define MAX_FILE_EXEC_THREAD 2

long n = 1000000;
long row = 100000;
long fcount = n/row;
int cur = 0;
FILE* fp = nullptr;

pthread_mutex_t m = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t t = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t c = PTHREAD_COND_INITIALIZER;


void Qsort(char **buf, long start, long end) {
    long i = start;
    long j = end;
    char* tmp = buf[i];
    if ( i<j ){
        while( i<j ){
            while( i<j && strcmp(buf[j] , tmp) >= 0 )
                j--;
            buf[i] = buf[j];
            while( i<j && strcmp(buf[i],tmp) <= 0 )
                i++;
            buf[j] = buf[i];
        }
        buf[i] = tmp;
        Qsort( buf , start , i-1 );
        Qsort( buf , i+1 , end );
    }
}

struct Job {
    __attribute__((unused)) FILE* fp;
    FILE* fd;
} job[MAX_FILE_EXEC_THREAD];

void* seprateData ( void*  ){

    struct Job work = {};
    int workCount;

    for(;cur>=0;){
        workCount = -1;
        pthread_mutex_lock(&m);

        if(0==cur){
            pthread_cond_wait(&c,&m);
        }
        if(cur>0){
            work = job[0];
            --cur;
            for(workCount=0;workCount<cur;workCount++)
                job[workCount] = job[workCount+1];
            //printf("get task succees\n");
        }

        pthread_mutex_unlock(&m);
        if(workCount != -1){
            long i;

            //pthread_mutex_lock(&m);
            char** buf = new char* [row];
            for (i = 0; i < row; i++)
                buf[i] = new char[LEN];

            printf("read file \n");

            long j;
            long count;
            count = 0;
            long count_c;

            pthread_mutex_lock(&m);
            for ( j=0 ; j<row ; j++){
                if(EOF == fscanf(fp,"%s\n",buf[j]))
                    break;
                count++;
            }
            printf("read data succeed\n");
            count_c = count;
            pthread_mutex_unlock(&m);

            Qsort(buf,0,count_c-1);
            printf("sort data succeed\n");

            for( j=0 ; j<count_c ; j++){
                fprintf(work.fd,"%s\n",buf[j]);
            }
            printf("write data succeed\n");

            for( i=0 ; i<row ; i++){
                delete[] buf[i];
            }
            delete[] buf;

            printf("free memory succees\n");

            printf("read file succeed\n");

        }
    }
    return nullptr;
}

void separate(char* src)
{
    time_t t_start = time(nullptr);
    int i;

    fp = fopen(src,"r");


    FILE* fd[fcount];
    char fileName[30] = { 0 };

    for( i=0 ; i<(int)fcount ; i++){
        sprintf(fileName , "file%d" , i);
        fd[i] = fopen(fileName , "w+r");
        printf("open dest file%d succeed\n" , i);
    }

    pthread_t id[MAX_FILE_EXEC_THREAD];
    for( i=0 ; i < MAX_FILE_EXEC_THREAD ; i++){
        pthread_create (&id[MAX_FILE_EXEC_THREAD], nullptr, seprateData, nullptr);
    }

    for( i=0 ; i<fcount ; i++){
        while(cur == MAX_FILE_EXEC_THREAD)
            usleep(100);
        pthread_mutex_lock(&m);
        job[cur].fp =fp;
        job[cur].fd = fd[i];
        ++cur;
        pthread_mutex_unlock(&m);
        pthread_cond_signal(&c);
    }

    while(cur>0)
        usleep(100);
    pthread_mutex_lock(&m);
    cur = -1;
    pthread_mutex_unlock(&m);
    pthread_cond_broadcast(&c);

    for( i=0 ; i < MAX_FILE_EXEC_THREAD ; i++){
        pthread_join(id[i], nullptr);
    }

    pthread_mutex_destroy(&m);
    pthread_mutex_destroy(&t);
    pthread_cond_destroy(&c);

    fclose(fp);

    time_t t_end = time(nullptr);
    printf("total time is %ld\n",t_end - t_start);
}


void mergeSort1(char* dest){

    FILE* fs = fopen(dest , "w");

    FILE** fd = new FILE* [fcount];
    for( int i=0 ; i<fcount ; i++){
        char fileName[30] = {0};
        sprintf(fileName,"file%d",i);
        fd[i] = fopen(fileName,"r");
    }

    /* Get one data from each file */
    char** data = new char* [fcount];
    for( int i=0 ; i<fcount; i++ ){
        data[i] = new char[LEN];
    }

    /* Mark wether the file is ended */

    bool* flag = new bool [fcount];
    for( int i=0 ; i<fcount ; i++){
        flag[i] = true;
    }

    printf("merge sort start\n");

    /* Read the first dat of each file into the buffer */
    for( int i=0 ; i<fcount ; i++){
        if(fscanf(fd[i],"%s\n",data[i])==EOF)
            flag[i] = false;
    }

    char* min = data[0];
    int cou = (int)fcount ;
    time_t t_start,t_end;

    t_start = time(nullptr);

    while( cou != 0){

        /* Find the minimum data and record the index */
        int index = 0;
        for( int j=0 ; j<fcount ; j++ ){
            if( flag[j] && strcmp(min,data[j])>=0 ){
                min = data[j];
                index = j;
            }
        }

        fprintf( fs, "%s\n" , min );

        if( fscanf(fd[index],"%s\n",data[index]) == EOF){
            flag[index] = false;
            cou--;
            for( int j=0 ; j<fcount; j++){
                if( flag[j] && strcmp(min,data[j]) <= 0){
                    min = data[j];
                }
            }
        }

    }

    t_end = time(nullptr);

    printf("merge ended ,total time is %ld\n", t_end - t_start);

    delete[] flag;
    delete[] data;
    for( int j=0 ; j<fcount ; j++ ){
        fclose(fd[j]);
    }
    delete[] fd;

    fclose(fs);

}




void mergeSort(char * dest){
    int fCount = 10;
    FILE * fs = fopen(dest, "w");
    FILE** fd = new FILE*[fCount];
    for(int i = 0; i < fCount; i++)
    {
        char fileName[30] = { 0 };
        sprintf(fileName , "file%d" , i);
        fd[i] = fopen(fileName, "r");
    }

    /* Get one data from each file */
    char** data = new char*[fCount];
    for(int i=0;i<fCount;i++)
    {
        data[i]=new char[LEN];
    }

    /* Mark wether the file is end */

    bool *flag = new bool[fCount];
    memset(flag, 1, sizeof(bool) * fCount);
    cout << "Merge start" << endl;
    int i;
    i = 0;

    /* Read the first data of each file into the buf*/
    for(i = 0; i < fCount; i++)
    {
        if(fscanf(fd[i], "%s\n", data[i])==EOF)
            flag[i] = false;
    }

    char* min=data[0];
    int cou = fCount;
    time_t t_start,t_end;
    t_start = time(nullptr);
    while( cou != 0)
    {
        /* Find the minimum data and record the index*/
        int j = 0;
        for(int i1 = 0; i1 < fCount; i1++)
        {    if(flag[i1] && strcmp(min, data[i1]) >= 0)
            {     	min = data[i1];
                j = i1;
            }

        }
        fprintf(fs, "%s\n", min);
        if(fscanf(fd[j], "%s\n", data[j]) == EOF)
        {
            flag[j] = false;
            cou--;
            for(int i2 = 0; i2 < fCount; i2++)
            {
                if(flag[i2] && strcmp(min, data[i2]) <= 0)
                {
                    min=data[i2];
                }
            }

        }

    }
    t_end = time(nullptr);
    cout << "Merge ended " << endl;
    cout << "Merge total time is " << t_end - t_start << endl;
    delete [] flag;
    delete [] data;

    for(int i3 = 0; i3 < fCount; i3++)
    {
        fclose(fd[i3]);
    }
    delete [] fd;
    fclose(fs);
}

void clear(){
    char fileName[30] = { 0 };
    for( int i=0 ; i<fcount ; i++ ){
        sprintf(fileName,"file%d",i);
        remove(fileName);
    }
}

int main (int argc , char* argv[]){

    if(argc<3){
        printf("USE:<%s> <source> <dest>\n",argv[0]);
        return -1;
    }

    time_t t_start = time(nullptr);

    separate(argv[1]);

    mergeSort(argv[2]);

    time_t t_end = time(nullptr);

    printf("the total time is %ld\n",t_end - t_start);

    //clear();

    return 0;
}