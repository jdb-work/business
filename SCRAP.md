SCRAP.md
========

```
def loaded = {
    new SvcCtx (
        'orac:getCustomer' {
            reqfn: "GetCustomer",
            req: {  }
            ) { this.reqfun = reqfun
                this.reqpar = reqpar
            }
        }
    )
}
```

```groovy
catch (ArrayIndexOutOfBoundsException aioobe) {
//todo: generalize error response
return """
<error>No address found for ${_id}</error>


<address>
    <addressLine1></addressLine1>
    <addressLine2></addressLine2>
    <addressLine3></addressLine3>
    <addressLine4></addressLine4>
    <city></city>
    <countryCode></countryCode>
    <countyCode></countyCode>
    <mailingName></mailingName>
    <mailingNameSecondary></mailingNameSecondary>
    <postalCode></postalCode>
    <stateCode></stateCode>
</address>
"""

//def wsdl = "https://oakdbs01:8182/DV910/${ctx.reqfun}?wsdl"
//SOAPClient client = new SOAPClient( wsdl )
//SOAPResponse response = client.send(sslTrustAllCerts:true) {
```

```groovy
def call(SvcCtx ctx) {
try {
    SOAPClient client = new SOAPClient("https://oakdbs01:8182/DV910/${ctx.reqfun}?wsdl")
    def response = client.send(sslTrustAllCerts:true) {
        ctx.@envelope   //'envelopeAttributes' ( )
        ctx.@soapver    //version SOAPVersion.V1_1
        ctx.@header     //header {
        ctx.@wssec      //'wsse:Security'( ctx.@wssext, ctx.@secutil ) { usertoken }
        ctx.@body       //body {
        ctx.@msg        //request }
    }
    assert response != null
    return (ctx.resp = response)
}
catch (SOAPFaultException sfe) {
    println sfe.message //fault
    println sfe.text    //envelope
    println sfe.httpResponse.statusCode
}
catch (SOAPClientException sce) {
    //indicates client error (i.e., 404 Not Found)
    println 'SCE: ' + sce.cause
}
catch (ArrayIndexOutOfBoundsException aioobe) {
    //todo: generalize error response
    return """
    <error>No address found for ${_id}</error>
    <address>
        <addressLine1></addressLine1>
        <addressLine2></addressLine2>
        <addressLine3></addressLine3>
        <addressLine4></addressLine4>
        <city></city>
        <countryCode></countryCode>
        <countyCode></countyCode>
        <mailingName></mailingName>
        <mailingNameSecondary></mailingNameSecondary>
        <postalCode></postalCode>
        <stateCode></stateCode>
    </address>
    """
}
```

```groovy
'wsse:UsernameToken' { //('wsu:Id': "UsernameToken-13") {}
````

```groovy
def rex = "(?=<address>)|(?<=</address>)"
def el = "city"
```

```groovy
def "Address"() {}
def "Amount"() {}
def "GetProperty"() {}
def "SetProperty"() {}
def "InvokeMethod"() {}
def "GetMetaClass"() {}
def "SetMetaClass"() {}
```
```groovy
package bssv

final class Address {
    def street
}

@Category(String)
class bssv.ops.CustomerOps {

    static String address(String self) {
        new Address(street: self)
    }

    static String id(String self) {
        String id = self ?: "250780"
    }

}
```

```groovy
@Grab(group='com.thoughtworks.xstream', module='xstream', version='1.4.1')
@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.0')
```

```groovy
package bssv
//@Grab(group='xpp3', module='xpp3_min', version='1.1.4c')
//@Grab(group='com.thoughtworks.xstream', module='xstream', version='1.3')
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute

@XStreamAlias("address")
class CustomerSpace {
    @XStreamAsAttribute
    @XStreamAlias('city')
    def city
}
final def msg = new CustomerSpace(city:'Savannah')

def stream = new XStream()
def obj
new File("john.xml").withOutputStream { out ->
    stream.toXML(msg, out)
}

new File("john.xml").withInputStream { ins ->
    obj = stream.fromXML(ins)
}

println obj
```