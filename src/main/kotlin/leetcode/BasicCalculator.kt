package leetcode

// Also supports multiplication and division with the correct precedences
class BasicCalculator {
    enum class Operator {
        PLUS,
        MINUS,
        TIMES,
        DIVIDE;

        fun infixBindingPower(): Pair<Double, Double> =
            when (this) {
                PLUS -> Pair(1.0, 1.1)
                MINUS -> Pair(1.0, 1.1)
                TIMES -> Pair(2.0, 2.1)
                DIVIDE -> Pair(2.0, 2.1)
            }

        fun prefixBindingPower(): Pair<Unit, Double> =
            when (this) {
                PLUS -> Pair(Unit, 5.0)
                MINUS -> Pair(Unit, 5.0)
                else -> throw IllegalStateException("Operator ${this.name} cannot be used as prefix operator")
            }
    }

    sealed interface Token {
        data class Number(val n: Int) : Token
        data class Operator(val op: BasicCalculator.Operator) : Token
        data object OpenParen : Token
        data object ClosedParen : Token
    }

    sealed interface Expression {
        data class Atom(val n: Int) : Expression
        data class BinaryExpression(val left: Expression, val right: Expression, val op: Operator) : Expression
        data class NegatedExpression(val expression: Expression) : Expression
    }

    private fun tokenize(s: String): List<Token> = buildList {
        var i = 0
        while (i < s.length) {
            when (s[i]) {
                ' ' -> {}
                '+' -> add(Token.Operator(Operator.PLUS))
                '-' -> add(Token.Operator(Operator.MINUS))
                '*' -> add(Token.Operator(Operator.TIMES))
                '/' -> add(Token.Operator(Operator.DIVIDE))
                '(' -> add(Token.OpenParen)
                ')' -> add(Token.ClosedParen)
                in '0'..'9' -> {
                    var n = 0
                    while (i < s.length && s[i].isDigit()) {
                        n *= 10
                        n += s[i] - '0'
                        i++
                    }
                    i--
                    add(Token.Number(n))
                }

                else -> throw IllegalStateException("Malformed expression")
            }
            i++
        }
    }

    fun parse(tokens: List<Token>): Expression {
        var i = 0

        fun peek(): Token? =
            if (i < tokens.size) tokens[i]
            else null

        fun next(): Token? =
            if (i < tokens.size) tokens[i++]
            else null

        fun loop(minBp: Double): Expression {
            var lhs =
                when (val token = next()) {
                    Token.OpenParen -> loop(0.0).also { assert(next() == Token.ClosedParen) }
                    is Token.Number -> Expression.Atom(token.n)
                    is Token.Operator -> {
                        val (_, rBp) = token.op.prefixBindingPower()
                        Expression.NegatedExpression(loop(rBp))
                    }

                    else -> throw IllegalStateException("Malformed expression")
                }

            while (true) {
                val op =
                    when (val token = peek()) {
                        Token.ClosedParen, null -> break
                        is Token.Operator -> token.op
                        else -> throw IllegalStateException("Malformed expression")
                    }
                val (lBp, rBp) = op.infixBindingPower()
                if (lBp < minBp) break
                next()

                val rhs = loop(rBp)
                lhs = Expression.BinaryExpression(lhs, rhs, op)
            }

            return lhs
        }

        return loop(0.0)
    }

    private fun evaluate(expression: Expression): Int =
        when (expression) {
            is Expression.Atom -> expression.n
            is Expression.NegatedExpression -> -evaluate(expression.expression)
            is Expression.BinaryExpression -> {
                val left = evaluate(expression.left)
                val right = evaluate(expression.right)
                val op = expression.op
                when (op) {
                    Operator.PLUS -> left + right
                    Operator.MINUS -> left - right
                    Operator.TIMES -> left * right
                    Operator.DIVIDE -> left / right
                }
            }
        }

    // Combines parsing and evaluation to avoid the construction of the AST
    private fun parseAndEvaluate(tokens: List<Token>): Int {
        var i = 0

        fun peek(): Token? =
            if (i < tokens.size) tokens[i]
            else null

        fun next(): Token? =
            if (i < tokens.size) tokens[i++]
            else null

        fun loop(minBp: Double): Int {
            var lhs =
                when (val token = next()) {
                    Token.OpenParen -> loop(0.0).also { assert(next() == Token.ClosedParen) }
                    is Token.Number -> token.n
                    is Token.Operator -> {
                        val (_, rBp) = token.op.prefixBindingPower()
                        -loop(rBp)
                    }

                    else -> throw IllegalStateException("Malformed expression")
                }

            while (true) {
                val op =
                    when (val token = peek()) {
                        Token.ClosedParen, null -> break
                        is Token.Operator -> token.op
                        else -> throw IllegalStateException("Malformed expression")
                    }
                val (lBp, rBp) = op.infixBindingPower()
                if (lBp < minBp) break
                next()

                val rhs = loop(rBp)
                lhs = when (op) {
                    Operator.PLUS -> lhs + rhs
                    Operator.MINUS -> lhs - rhs
                    Operator.TIMES -> lhs * rhs
                    Operator.DIVIDE -> lhs / rhs
                }
            }

            return lhs
        }

        return loop(0.0)
    }

    fun calculateInefficient(s: String): Int =
        evaluate(parse(tokenize(s)))

    fun calculate(s: String): Int =
        parseAndEvaluate(tokenize(s))
}
