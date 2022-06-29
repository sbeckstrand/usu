from re import X
from termios import B0
# from this import d
from PIL import Image
import numpy as np
import matplotlib.colors as col
import random
import scipy.stats

class prng(object):

    ### ================ Problem 1: Unit Tests =====================
    @staticmethod
    def lcg(a, b, m, n, x0=0):
        """
        returns an lcg generator that generates n random numbers with linear congruential generator
        given a, b, m, n, and x0 (i.e., seed). 
        """
        
        def lcg_gen():
            prev = x0;
            while True:
                x = ((a * prev) + b) %m
                yield x
                prev = x
        
        return lcg_gen


    @staticmethod
    def xorshift(a, b, c, n, x0=1):
        """
        returns a xorshift generator that generates n random numbers with xorshift
        given a, b, c, n, and x0 (i.e., seed). 
        """

        # def xor_gen():
        #     count = 0
        #     prev = x0
        #     while True:
        #         if count == 0:
        #             count += 1
        #             yield prev
                
                
        #         a0 = prev ^ (prev << a) & 0xFFFFFFFF
        #         a1 = a0 ^ (a0 >> b) & 0xFFFFFFFF
        #         b_n = a1 ^ (a1 << c) & 0xFFFFFFFF
        #         prev = b_n
        #         yield b_n

        # return xor_gen

        # def xor_gen():
        #     count = 0
        #     xn = x0
        #     while True:
        #         if count == 0:
        #             count += 1
        #             yield xn


        #         a0 = xn ^ (xn << a) & 0xFFFFFFFF
        #         a1 = a0 ^ (a0 >> b) & 0xFFFFFFFF
        #         b1 = a1 ^ (a1 << c) & 0xFFFFFFFF
        #         xn = b1
        #         yield xn

        # return xor_gen

        def xor_gen():
            xn = x0
            yield xn
            while True:
                a0 = xn ^ (xn << a) & 0xFFFFFFFF
                a1 = a0 ^ (a0 >> b) & 0xFFFFFFFF
                b1 = a1 ^ (a1 << c) & 0xFFFFFFFF
                xn = b1
                yield xn
        return xor_gen

    @staticmethod    
    def mersenne_twister(n, x0=1, start=0, stop=1000):
        """
        returns a mersenne twister generator (the python generator) to generate n random numbers 
        given the seed x0 which defaults to 1.
        """
        
        def mersenne_gen():
            while True:
                yield random.randint(start,stop)
        
        return mersenne_gen

    @staticmethod
    def __get_byte(i, byte_index):
        i = int(i)
        return ((i >> (8 * byte_index)) % 256 + 256) % 256

    @staticmethod
    def __int_to_rgb(i):
        r = prng.__get_byte(i, 0) / 255
        g = prng.__get_byte(i, 1) / 255
        b = prng.__get_byte(i, 2) / 255
        return [r, g, b]

    @staticmethod
    def gen_lcg_data(a, b, m, n, seed=0, option=1):
        lcgg = prng.lcg(a, b, m, n, x0=seed)()
        
        ### option 1) generate n lcg numbers.
        if option == 1:
            return np.array([next(lcgg) for _ in range(n)])

        ### option 2) generate n lcg numbers by
        ### varying the seed from i upto n-1.
        elif option == 2:
            data = np.zeros(n)
            for i in range(n):
                data[i] = next(prng.lcg(a, b, m, 1, x0=i)())
            return data
        
        ### option 3) generate n numbers with numpy arange.
        elif option == 3:
            return np.arange(n)

        else:
            raise Exception('prng.get_lcg_data(): option must be 1,2,3')
      
    @staticmethod
    def gen_xorshift_data(a, b, c, n, seed=1, option=1):
        xsg = prng.xorshift(a, b, c, n, x0=seed)()

        ### Option 1) Generate n xorshift numbers.
        if option == 1:
            return np.array([next(xsg) for _  in range(n)])

        elif option == 2:
            data = np.zeros(n)
            for i in range(n):
                data[i] = next(prng.xorshift(a, b, c, 1, i)())
            return data
        
        elif option == 3:
            return np.arange(n)
        
        else:
            raise Exception('prng.get_lcg_data(): option must be 1,2,3')

    @staticmethod
    def gen_mersenne_twister_data(n, seed=1, start=0, stop=1000, option=1):
        mtw = prng.mersenne_twister(n, seed, start, stop)()

        if option == 1:
            return np.array([next(mtw) for _ in range(n)])
        
        elif option == 2:
            data = np.zeros(n)
            for i in range(n):
                data[i] = next(prng.mersenne_twister(1, i, start, stop)())
            return data
        
        elif option ==3:
            return np.arange(n)
        
        else:
            raise Exception('prng.get_lcg_data(): option must be 1,2,3')

    @staticmethod
    def gen_pil_image(data, w, h, n, name='tmp', save_flag=True):
        """
        generate a PIL image from data and display/save it.
        """
        img_data = np.zeros((n, 3))
        
        for i, j in enumerate(data):
            img_data[i] = prng.__int_to_rgb(j)

        img_data = img_data.reshape(h, w, 3)
        
        pil_img = Image.fromarray(img_data, 'RGB')
        
        ### save PIL image in the current directory
        if save_flag:
            pil_img.save(name + '.png')
        else:
            pil_img.show()


    ### =============== Problem 3 ===============================
    
    @staticmethod
    def equidistrib_test(seq, n, lower_bound, upper_bound):
        f_obs = []
        f_exp = []
        for i in range(lower_bound, upper_bound + 1):
            count = 0
            for j in range(0, n):
                if seq[j] == i:
                    count += 1
            
            f_obs.append(count)
            f_exp.append(n * (1 / (upper_bound + 1 - lower_bound)))

        results = scipy.stats.chisquare(f_obs, f_exp)
        return results






