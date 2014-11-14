package bssv.contexts

class EntityContext {
    def Closure reqpar
    def GString reqfun
    def int id = 250780
    def String username = "mlewis"
    def String password = "great2014"

    def EntityContext(GString reqfun, Closure reqpar) {
        this.reqfun = reqfun
        this.reqpar = reqpar
    }
}
