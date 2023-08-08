package minesweeper

import kotlin.random.Random

fun main() {
    val field = MutableList(9) { mutableListOf<Char>() }
    for (i in 0..8) {
        for (j in 0..8) {
            field[i].add(j, '.')
        }
    }
    println("How many mines do you want on the field?")
    var mines = readln().toInt()
    val hiddenField = MutableList(9) { mutableListOf<Char>() }
    for (i in 0..8) {
        for (j in 0..8) {
            hiddenField[i].add(j, '.')
        }
    }
    fun printField() {
        println(
            """
 │123456789│
—│—————————│
        """.trimIndent()
        )
        for (i in 0..8) {
            print("${i + 1}│")
            print(hiddenField[i].joinToString(""))
            println("│")
        }
        println("—│—————————│")
    }
    printField()
    println("Set/unset mine marks or claim a cell as free:")
    var prompt = readln().split(" ")
    while (mines != 0) {
        var i = Random.nextInt(0, 9)
        var j = Random.nextInt(0, 9)
        if (field[i][j] == field[prompt[1].toInt() - 1][prompt[0].toInt() - 1]) {
            field[i][j] = '*'
            mines--
        }
    }
    fun numbering(a: Char) {
        for (i in 1..7) {
            for (j in 1..7) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i - 1..i + 1) {
                        for (p in j - 1..j + 1) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (i in 0..0) {
            for (j in 1..7) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i..i + 1) {
                        for (p in j - 1..j + 1) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (j in 0..0) {
            for (i in 1..7) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i - 1..i + 1) {
                        for (p in j..j + 1) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (i in 8..8) {
            for (j in 1..7) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i - 1..i) {
                        for (p in j - 1..j + 1) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (j in 8..8) {
            for (i in 1..7) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i - 1..i + 1) {
                        for (p in j - 1..j) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (i in 0..0) {
            for (j in 0..0) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i..i + 1) {
                        for (p in j..j + 1) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (j in 8..8) {
            for (i in 0..0) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i..i + 1) {
                        for (p in j - 1..j) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (i in 8..8) {
            for (j in 0..0) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i - 1..i) {
                        for (p in j..j + 1) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
        for (j in 8..8) {
            for (i in 8..8) {
                if (field[i][j] != a) {
                    var count = 0
                    for (k in i - 1..i) {
                        for (p in j - 1..j) {
                            if (field[k][p] == a) {
                                count++
                            }
                        }
                    }
                    if (count > 0) {
                        field[i][j] = count.digitToChar()
                    }
                }
            }
        }
    }
    numbering('*')
    fun check(): Boolean {
        var check1 = false
        var check2 = false
        for (i in 0..8) {
            for (j in 0..8) {
                if (hiddenField[i][j] == '*') {
                    var count = 0
                    count++
                    if (field[i][j] == '*' && count == mines) check1 = true else false
                }
                if (hiddenField[i][j] == '.') {
                    if (field[i][j] == '*') check2 = true else false
                }
            }
        }
        var check = check1 || check2
        return check
    }
    while (check() == false) {
        if (prompt[2] == "mine") {
            hiddenField[prompt[1].toInt() - 1][prompt[0].toInt() - 1] = '*'
            printField()
            check()
        } else if (prompt[2] == "free") {
            if (field[prompt[1].toInt() - 1][prompt[0].toInt() - 1] == '.') {
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (prompt[1].toInt() - 1 + i >= 0 && prompt[0].toInt() - 1 + j >= 0 && prompt[1].toInt() - 1 + i <= 8 && prompt[0].toInt() - 1 + j <= 8) {
                            if (field[prompt[1].toInt() - 1 + i][prompt[0].toInt() - 1 + j] == '.') {
                                hiddenField[prompt[1].toInt() - 1 + i][prompt[0].toInt() - 1 + j] = '/'
                                fun emptyCells(a: Char) {
                                    for (i in 1..7) {
                                        for (j in 1..7) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i - 1..i + 1) {
                                                    for (p in j - 1..j + 1) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (i in 0..0) {
                                        for (j in 1..7) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i..i + 1) {
                                                    for (p in j - 1..j + 1) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (j in 0..0) {
                                        for (i in 1..7) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i - 1..i + 1) {
                                                    for (p in j..j + 1) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (i in 8..8) {
                                        for (j in 1..7) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i - 1..i) {
                                                    for (p in j - 1..j + 1) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (j in 8..8) {
                                        for (i in 1..7) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i - 1..i + 1) {
                                                    for (p in j - 1..j) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (i in 0..0) {
                                        for (j in 0..0) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i..i + 1) {
                                                    for (p in j..j + 1) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (j in 8..8) {
                                        for (i in 0..0) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i..i + 1) {
                                                    for (p in j - 1..j) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (i in 8..8) {
                                        for (j in 0..0) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i - 1..i) {
                                                    for (p in j..j + 1) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (j in 8..8) {
                                        for (i in 8..8) {
                                            if (hiddenField[i][j] == a) {
                                                for (k in i - 1..i) {
                                                    for (p in j - 1..j) {
                                                        if (field[k][p] == '.') {
                                                            hiddenField[k][p] = '/'
                                                        }
                                                        if (field[k][p] in '1'..'9') {
                                                            hiddenField[k][p] =
                                                                field[k][p]
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                emptyCells('/')
                            }
                            if (field[prompt[1].toInt() - 1 + i][prompt[0].toInt() - 1 + j] in '1'..'9') {
                                hiddenField[prompt[1].toInt() - 1 + i][prompt[0].toInt() - 1 + j] =
                                    field[prompt[1].toInt() - 1 + i][prompt[0].toInt() - 1 + j]
                            }
                        }
                    }
                }
                printField()
            } else if (field[prompt[1].toInt() - 1][prompt[0].toInt() - 1] in '1'..'9') {
                hiddenField[prompt[1].toInt() - 1][prompt[0].toInt() - 1] =
                    field[prompt[1].toInt() - 1][prompt[0].toInt() - 1]
                printField()
            } else break
            check()
        }
        println("Set/unset mine marks or claim a cell as free:")
        prompt = readln().split(" ")
    }
    when (check()) {
        true -> {
            for (i in 0..8) {
                for (j in 0..8) {
                    if (field[i][j] in '1'..'9') {
                        hiddenField[i][j] = field[i][j]
                    } else if (field[i][j] == '.') {
                        hiddenField[i][j] = '/'
                    }
                }
            }
            printField()
            println("Congratulations! You found all the mines!")
        }
    }
    if (field[prompt[1].toInt() - 1][prompt[0].toInt() - 1] == '*') {
        for (i in 0..8) {
            for (j in 0..8) {
                if (field[i][j] == '*') {
                    hiddenField[i][j] = 'X'
                }
            }
        }
        printField()
        println("You stepped on a mine and failed!")
    }
}