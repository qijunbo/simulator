echo "ocpp1.2"
md ocpp12
xjc -wsdl  ocpp\cs\_2010\_08\CentralSystemService.wsdl -d .\ocpp12
xjc -wsdl  ocpp\cp\_2010\_08\ChargePointService.wsdl -d  .\ocpp12
 
echo "ocpp1.5"
md ocpp15
xjc -wsdl  ocpp\cs\_2012\_06\ocpp_centralsystemservice_1.5_final.wsdl -d .\ocpp15
xjc -wsdl  ocpp\cp\_2012\_06\ocpp_chargepointservice_1.5_final.wsdl -d .\ocpp15

echo "soapenvelope"
