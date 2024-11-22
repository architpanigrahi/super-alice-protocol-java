# super-alice-protocol-java

## Clean Build project
`./gradlew clean build`

## Start bootstrap peer
`./gradlew run --args="-t BOOTSTRAP_NODE -p 33000"`

## Start satellite peer
`./gradlew run --args="-t SATELLITE -p 33100 -b 127.0.0.1:33000 -i 10000"`

## Start edge peer
`./gradlew run --args="-t EDGE_DEVICE -p 33200 -b 127.0.0.1:33000 -i 20000"`
