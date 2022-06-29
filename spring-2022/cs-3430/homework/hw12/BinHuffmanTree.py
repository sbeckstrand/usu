#!/usr/bin/python3

##################################################
# module: BinHuffmanTree.py
# author: Vladimir Kulyukin
# HuffmanTree for encoding and decoding bytes
# bugs to vladimir kulyukin in canvas.
##################################################

from array import array
import _pickle as pickle
from HuffmanTree import HuffmanTree

class BinHuffmanTree(HuffmanTree):

    def __init__(self, root=None):
        super(BinHuffmanTree, self).__init__(root=root)
    
    def encodeText(self, txt):
        bits = super(BinHuffmanTree, self).encodeText(txt)
        n = len(bits)
        bin_array = array('B')
        padded_bits = 0
        if n <= 8:
            bits = bits + '0' * (8-n)
            padded_bits = 8 - n
            bin_array.append(int(bits[::-1], 2))
        else:
            r = n % 8
            if r > 0:
                bits = bits + '0' * (8 - r)
                padded_bits = 8 - r
            for i in range(0, len(bits), 8):
                byte = bits[i:i+8][::-1]
                #print byte
                bin_array.append(int(byte, 2))
        return bin_array, len(bits)//8, padded_bits

    def encodeTextToFile(self, txt, fp):
        bin_array, num_bytes, padded_bits = self.encodeText(txt)
        with open(fp + '.bin', 'wb') as of:
            bin_array.tofile(of)
            of.flush
        with open(fp + '_pb.txt', 'w') as of:
            of.write(str(num_bytes)+'\n')
            of.write(str(padded_bits)+'\n')
            of.flush()

    def encodeTextFromFileToFile(self, input_fp, output_fp):
        with open(input_fp, 'r',  encoding='utf-8') as inf:
            data = inf.read()
            self.encodeTextToFile(data, output_fp)

    def decodeTextFromFile(self, fp):
        num_bytes, padded_bits = 0, 0
        bin_array = array('B')
        with open(fp + '_pb.txt', 'r',  encoding='utf-8') as inf:
            num_bytes = int(inf.readline().rstrip())
            padded_bits = int(inf.readline().rstrip())
        with open(fp + '.bin', 'rb') as inf:
            bin_array.fromfile(inf, num_bytes)
        return self.decode(bin_array, padded_bits)
    
    def decode(self, bin_array, padded_bits):
        bits = ''
        for byte in bin_array:
            bits += '{0:08b}'.format(byte)[::-1]
        bits = bits[:len(bits)-padded_bits]
        return super(BinHuffmanTree, self).decode(bits)

    def persist(self, fp):
        with open(fp, 'wb') as outfile:
            pickle.dump(self, outfile)
            outfile.flush()
    
    @staticmethod
    def load(fp):
        with open(fp, 'rb') as infile:
            bht = pickle.load(infile)
            return bht
