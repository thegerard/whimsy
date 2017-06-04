package norswap.autumn.naive

import norswap.autumn.TokenGenerator
import norswap.autumn.TokenGrammar
import norswap.autumn.parsers.string
import kotlin.reflect.full.functions


class Token (val g: TokenGrammar, val value: TokenGenerator?, val token: ()-> Boolean): Parser() {
    constructor (g: TokenGrammar, str: String): this(g, null, Str(g, str))
    constructor (g: TokenGrammar, token: ()-> Boolean): this(g, null, token)
    override fun invoke() = value?.let { g.token(value) {token() }() }?: g.token { token() }()
}

class StrToken (val g: TokenGrammar, val token: String): Parser() {
    override fun invoke() = g.token { grammar.string(token) }()
}

class Kword(val g: TokenGrammar, val keyword: String): Parser() {
    override fun invoke() = g.token ({ null }) { grammar.string(keyword) }()
}

class TokenChoice (val g: TokenGrammar, vararg val tokens: ()-> Boolean): Parser() {
    override fun invoke() = g.token_choice(*(tokens.map { {it()} }).toTypedArray())()
}