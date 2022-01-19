
from kafka-python import KafkaProducer

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    producer = KafkaProducer(bootstrap_servers='localhost:9092')
    for _ in range(100):
        producer.send('foobar', b'some_message_bytes')

