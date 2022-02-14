fun main(args: Array<String>) {

    var chunksInputString:String = "[({(<(())[]>[[{[]{<()<>>\n" +
                                     "[(()[<>])]({[<{<<[]>>(\n" +
                                   "{([(<{}[<>[]}>{[]{[(<()>\n" +
                                    "(((({<>}<{<{<>}{[]{[]{}\n" +
                                     "[[<[([]))<([[{}[[()]]]\n" +
                                    "[{[{({}]{}}([{[{{{}}([]\n" +
                                    "{<[[]]>}<{[{[{[]{()[[[]\n" +
                                     "[<(<(<(<{}))><([]([]()\n" +
                                     "<{([([[(<>()){}]>(<<{{\n" +
                                     "<{([{{}}[<[[[<>{}]]]>[]]"

    val openingBrackets = charArrayOf('[','(','{', '<')
    val closingBrackets = charArrayOf(']',')','}', '>')
    var myChunk = ""
    var syntaxErrorScore = 0
    var listOfChunks = chunksInputString.lines()


    for(chunk in listOfChunks){
        myChunk = chunk

        //Simplify chunk
        while (myChunk.contains("[]") || myChunk.contains("()") ||
               myChunk.contains("{}") || myChunk.contains("<>")){

            myChunk = myChunk.replace("[]", "")
            myChunk = myChunk.replace("()", "")
            myChunk = myChunk.replace("{}", "")
            myChunk = myChunk.replace("<>", "")
        }
    //check if valid
        when{
            myChunk.isEmpty() -> println("$chunk is a legal chunk $myChunk")  //legal chunk
            myChunk.all { openingBrackets.contains(it) } -> println("$chunk is incomplete chunk $myChunk") //incomplete chunk

            //check if chunk is corrupted
            myChunk.indexOfAny(closingBrackets) >= 0 -> {
                println("$chunk is corrupted chunk $myChunk")

                //Stop at the first incorrect closing character
                for (i in 0 until myChunk.length){
                    if (closingBrackets.contains(myChunk[i])){
                        var found = myChunk[i]
                        when(myChunk[i-1]){
                            '(' -> println("Expected ), but found $found instead.")
                            '{' -> println("Expected }, but found $found instead.")
                            '[' -> println("Expected ], but found $found instead.")
                            '<' -> println("Expected >, but found $found instead.")
                        }

                        when(found){
                            ')' -> syntaxErrorScore += 3
                            '}' -> syntaxErrorScore += 57
                            ']' -> syntaxErrorScore += 1197
                            '>' -> syntaxErrorScore += 25137
                        }
                    break
                    }
                }
            }
        }
    }
    println("SyntaxErrorScore: $syntaxErrorScore ")
    syntaxErrorScore = 0
}



