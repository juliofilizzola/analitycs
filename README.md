﻿# Analytics

Essa API é construída em Java e tem como objetivo mais armazenar dados de analise feitas a parti da [API REST](https://github.com/juliofilizzola/java-sping-bot-kafka).

Ela possui uma conexão com o Apache Kafka, que ao receber as informações geradas pela Api Rest, converte em dados mais objetivos, como media de preço, quantidade de carros por modo e quantidade de carros por marca.

## Composição

- Springboot
- Postgresql
- Kafka

## Base

Ele está configurado com springboot versão 3.1.3 e com o Java versão 17.

Possui um arquivo docker-compose para criação do banco de dados. 
