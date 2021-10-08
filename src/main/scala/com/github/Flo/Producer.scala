package com.github.Flo

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.logging._

import java.util.Properties
import java.util.logging.{Level, Logger}


object Producer {
  def main(args: Array[String]): Unit ={

    // Set the log level to only print errors
    //Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("com").setLevel(Level.WARNING)

    println("Hello you")

    val serverIP = "localhost:9092"
    val clientID = "ScalaProducer"
    val acks = "all"

    val strSerializer = "org.apache.kafka.common.serialization.StringSerializer"

    // create a config
    val properties = new Properties()

    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverIP)
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, strSerializer )
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, strSerializer)
    properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, clientID)
    properties.setProperty(ProducerConfig.ACKS_CONFIG, acks)

    // Create a producer with config
    val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](properties)

    val topic = "first_test_topic"
    println(s"Sending records in Kafka Topics [$topic]")

    //Create a record
    val record: ProducerRecord[String, String] = new ProducerRecord(topic, "hello from scala project")

    producer.send(record, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        if(exception == null){
          println(s"metadata : ${metadata.topic()}")
        } else {
          println(s"execption : ${exception}")
        }
      }
    })

    producer.close()
  }

}
