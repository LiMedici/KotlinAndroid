package dsl

fun html(block:Tag.()->Unit):Tag{
    return Tag("html").apply(block)
}

fun Tag.head(block:Head.()->Unit){
    this + Head().apply(block)
}

fun Tag.body(block:Body.()->Unit){
    this + Body().apply(block)
}

class StringNode(val content:String):Node{
    override fun render(): String {
        return content
    }
}

class Head:Tag("head")
class Body:Tag("body"){
    var id by MapDelegate(properties)

    var `class` by MapDelegate(properties)
}