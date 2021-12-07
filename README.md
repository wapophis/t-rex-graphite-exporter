# t-rex-graphite-exporter

This is a tool to export data from some apis to a graphite enabled server, then you can use this data from grafana dashboards.
Main api is the t-rex-miner exported api, you can connect locally this api using the restriced config to 127.0.0.1

## Installation

### Prerequisites
JDK 1.8 or above

### Execution

This tools use java to runs, so use java -jar <package-name.jar> to run properly

### Configuration  

```

usage: java -jar trexexporter.jar
 -aef,--aemet-flush-interval <arg>       Setup aemet client flush time.
                                         Set a value to enable integration
 -aes,--aemet-idema-station-id <arg>     Setup aemet station id to query
                                         for
 -aet,--aemet-token <arg>                Setup aemet api token
 -cport,--carbon-port <arg>              Carbon database tcp port
 -curl,--carbon-url <arg>                Carbon database endpoint
 -dry,--dry-run                          Enable Dry-run mode, no send data
                                         to carbon server
 -eif,--eisos-flush-interval <arg>        Calc price power consumption in
                                         spain using eisos api to retrieve
                                         current electrical cost. Setup
                                         the interval to report this info
                                         to the graphite server and calc
                                         cadency.
 -tpoll,--t-rex-polling-interval <arg>   Polling interval to the trex api
                                         in milliseconds
 -turl,--t-rex-url <arg>                 Trex api endpoint
 -xcap,--xtra-capital-invested <arg>     Capital invest to get break event
 -xpo,--xtra-power-offset <arg>          Offset to apply to the power
                                         consumption

```
                                         

### Exported Metrics

TBD             