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
### Supported integrations:
 
####T-Rex miner
Main api to export data. 
When startup setup de the T-Rex miner to expose the api:
 - turl: Uri in which the T-Rex miner is running
 - tpoll: Polling interval to the T-Rex miner api, exporter will flush T-Rex data to the carbon server at the same rate. 
                                          
##### Exported Metrics

 Metrics exported will be prefixed with the worker name settled in T-Rex at startup.
 
 ###### GPU DATA
 Every GPU will be prefixed with the T-Rex main prefix, and the {Vendor}.{Model}.{UUID} info. 
 
 - {WorkerName}.{Vendor}.{Model}.{UUID}.memTemp: Memory temperature if is supported.
 - {WorkerName}.{Vendor}.{Model}.{UUID}.coreTemp: GPU core temperatures. 
 - {WorkerName}.{Vendor}.{Model}.{UUID}.memClock: Memory clock.
 - {WorkerName}.{Vendor}.{Model}.{UUID}.coreClock: GPU core clock.
 - {WorkerName}.{Vendor}.{Model}.{UUID}.hashRate: HashRate from GPU.
 - {WorkerName}.{Vendor}.{Model}.{UUID}.fanSpeed: Fan speed percent from GPU.
 - {WorkerName}.{Vendor}.{Model}.{UUID}.power: Power consumption from this GPU. 
 
 ###### OPERATIONAL STATUS
  
 - {WorkerName}.trex.paused
 - {WorkerName}.trex.hashRate
 - {WorkerName}.trex.upTime
 - {WorkerName}.trex.running
 - {WorkerName}.trex.stopped
 
 ###### Shares's info
  - {WorkerName}.trex.acceptedCount: Number of accepted shares
  - {WorkerName}.trex.rejectedCount: Number of rejected shares
  - {WorkerName}.trex.invalidCount: Number of invalid shares
  - {WorkerName}.trex.solvedCount: Number of resolved shares
  - {WorkerName}.trex.shareRate: Processing share rate
  - {WorkerName}.trex.shareRate_avg: Average Processing share rates. 
  
  ###### Active pool info
  
  - {WorkerName}.trex.active_pool.retries: Number of retries to connect to current active pool. 
   
 
 ####Two-miners api pool:
 TBD
 
 ####EISOS (Spanish energy agency information )
 TBD
 
 ####AEMET (Spanish weather data)
 TBD
 
 #### Exchanges
 TBD