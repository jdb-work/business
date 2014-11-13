package bssv

abstract class BzSvc {

    def static segmenter
    def static entityId
    def static xml

    def scope
    def splitPosition
    def element

    static {
       xml = new BssvHandler().call(entityId).text.split(segmenter)[1]
    }

    BzSvc(scope, splitPosition, element) {
        this.scope = scope
        this.splitPosition = splitPosition
        this.element = element
    }

    def abstract value()
}


