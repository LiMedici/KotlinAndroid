package dsl

fun main(args: Array<String>) {
    html {
        "id"("HtmlId")
        "head"{
            "id"("HeadId")
        }

        body{
            id = "bodyId"
            `class` = "bodyClass"
            "a"{
                "href"("https://www.baidu.com")
                + "Kotlin 中文博客"
            }
        }
    }.render().let(::println)
}