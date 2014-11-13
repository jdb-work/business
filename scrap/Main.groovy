package scrap

import bzsvc.CustomerManager

@Grab(group='ch.qos.logback', module='logback-classic', version='1.0.13')

class Main {

    static void main(String[] args) {
        def service = new CustomerManager()
        service.getCustomer(250780)
    }
}
