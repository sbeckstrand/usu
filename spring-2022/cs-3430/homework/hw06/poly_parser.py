#!/usr/bin/python

#################################################
# module: poly_parser.py
# bugs to vladimir dot kulyukin in canvas
#################################################

from maker import maker


class poly_parser(object):

    @staticmethod
    def parse_elt(elt):
        # let's make sure that elt is a string.
        assert isinstance(elt, str)

        chars = elt.split('^')

        coeff = chars[0][0:-1]
        var = chars[0][-1]
        pow = chars[1]

        # Likely need to add condition for pow = 0
        out = maker.make_prod(maker.make_const(float(coeff)), maker.make_pwr(var, float(pow)))

        return out

    @staticmethod
    def parse_sum(poly_str):
        assert isinstance(poly_str, str)
        

        poly_minus = poly_str.rpartition(" - ")
        poly_plus =  poly_str.rpartition(" + ")

        if len(poly_minus[0]) > len(poly_plus[0]):
            return maker.make_plus(poly_parser.parse_sum(poly_minus[0]), poly_parser.parse_elt("-%s" % poly_minus[-1]))
        
        if len(poly_plus[0]) > len(poly_minus[0]):
            return maker.make_plus(poly_parser.parse_sum(poly_plus[0]), poly_parser.parse_elt(poly_plus[-1]))
        
        return poly_parser.parse_elt(poly_str)
