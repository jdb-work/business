package bssv.contexts

/**
todo: value injection
id = "250780",
username = "mlewis"
password = "great2014"
*/
def SvcCtx {
    def GString id
    def GString username
    def GString password
    def GString reqfn
    def Closure req
    def GString resp
}



