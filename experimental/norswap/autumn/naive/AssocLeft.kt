package norswap.autumn.naive
import norswap.autumn.Grammar
import norswap.autumn.parsers.AssocLeft
import norswap.autumn.parsers.assoc_left

/**
 * A parser that matches applications of a set of left-associative binary operators and
 * postfix operators.
 *
 * This parser takes an initialization function as parameter.
 *
 * Within that function, you must specify how to parse the left-hand side and right-hand side of
 * these operators by assigning the [left] and [right] properties. If both sides are recognized by
 * the same parser, assign [operands] instead. For postfix operators, only the left-hand side
 * is relevant.
 *
 * The operators themselves must be defined with one of the [op] or [postfix] functions.
 *
 * All operators explicitly handled by this parser have the same precedence, which is naturally
 * lower than that of the operators (if any) matched by [left] and [right].
 *
 * By default, the parser matches the same thing as its [left] property if no binary or postfix
 * operators are matched.  This is typically the desired behaviour when implementing expressions in
 * a language. This can be controlled through the [strict] property (should be set in the
 * initialization function).
 */
class AssocLeft (g: Grammar, val init: AssocLeft.() -> Unit): Parser()
{
    init {
        grammar = g
        parser = g.assoc_left(init)
    }
}