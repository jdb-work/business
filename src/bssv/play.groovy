package bssv

@Grab(group='xpp3', module='xpp3_min', version='1.1.4c')
@Grab(group='com.thoughtworks.xstream', module='xstream', version='1.3')
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
println obj.dump()
