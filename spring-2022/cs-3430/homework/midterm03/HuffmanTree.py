
################################################
## module: HuffmanTree.py
## description: A Huffman Tree Class
## YOUR NAME
## YOUR A#
##
## bugs to vladimir kulyukin in canvas
################################################

from HuffmanTreeNode import HuffmanTreeNode
    
class HuffmanTree(object):
    def __init__(self, root=None):
        self.__root = root

    def getRoot(self):
        return self.__root

    def encodeSymbol(self, s):
        if not s in self.__root.getSymbols():
            raise Exception('Unknown symbol')
        else:
            if self.__root.isLeaf():
                root_sym = next(iter(self.__root.getSymbols()))
                if root_sym == s:
                    return ""
            else:
                leftChild = self.__root.getLeftChild()
                if s in leftChild.getSymbols():
                    leftTree = HuffmanTree(leftChild)
                    return "0" + leftTree.encodeSymbol(s)
                
                rightChild = self.__root.getRightChild()
                if s in rightChild.getSymbols():
                    rightTree = HuffmanTree(rightChild)
                    return "1" +  rightTree.encodeSymbol(s)
                
                raise Exception("Donezo")

    def encodeText(self, txt):
        encoding = ""
        for s in txt:
            sym_enc = HuffmanTree.encodeSymbol(self, s)
            encoding += sym_enc
        
        return encoding
     
    def decode(self, bin_string):
        decoding = ""
        curr_node = self.__root

        for b in bin_string:
            if b == "0":
                curr_node = curr_node.getLeftChild()
            elif b == "1":
                curr_node = curr_node.getRightChild()
            else: 
                raise Exception("Value must be binary value 0 or 1")
                
            if curr_node.isLeaf():
                val = next(iter(curr_node.getSymbols()))
                decoding += val
                curr_node = self.__root
        
        return decoding


    
    @staticmethod
    def mergeTwoNodes(htn1, htn2):
        symbols = set(htn1.getSymbols())
        for i in htn2.getSymbols():
            symbols.add(i)
        n = HuffmanTreeNode(symbols=symbols, weight=htn1.getWeight() + htn2.getWeight())
        n.setLeftChild(htn1)
        n.setRightChild(htn2)
        return n

    @staticmethod
    def displayHuffmanTreeNode(ht_node, tabs):
        if ht_node is None:
            return
        print(tabs + '{}:{}'.format(ht_node.getSymbols(), ht_node.getWeight()))
        HuffmanTree.displayHuffmanTreeNode(ht_node.getLeftChild(), tabs+'\t')
        HuffmanTree.displayHuffmanTreeNode(ht_node.getRightChild(), tabs+'\t')

    @staticmethod
    def displayHuffmanTree(huff_tree):
        HuffmanTree.displayHuffmanTreeNode(huff_tree.getRoot(), '')

    @staticmethod
    def displayListOfNodes(list_of_nodes):
        for n in list_of_nodes:
            print(str(n))

    @staticmethod
    def fromListOfHuffmanTreeNodes(list_of_nodes, dbg=False):

        while len(list_of_nodes) > 1:

            node1 = list_of_nodes[0]
            node2 = list_of_nodes[1]

            if node2.getWeight() < node1.getWeight():
                node1 = list_of_nodes[1]
                node2 = list_of_nodes[0]

            for node in list_of_nodes:
                if node.getWeight() < node1.getWeight():
                    node2 = node1
                    node1 = node
                elif node.getWeight() < node2.getWeight() and node != node1:
                    node2 = node
            
            
            list_of_nodes.remove(node1)
            list_of_nodes.remove(node2)
                
            merged_node = HuffmanTree.mergeTwoNodes(node1, node2)
            list_of_nodes.append(merged_node)
        
        return HuffmanTree(list_of_nodes[0]) 

            


    @staticmethod
    def freqMapToListOfHuffmanTreeNodes(freq_map):
        return [HuffmanTreeNode(symbols=set([item[0]]), weight=item[1]) for item in freq_map.items()] 


char_freqs02 = [('A', 8), ('B', 3), ('C', 1), ('D', 1),
                    ('E', 1), ('F', 1), ('G', 1), ('H', 1)]

A_8 = HuffmanTreeNode(symbols=set(['A']), weight=8)
B_3 = HuffmanTreeNode(symbols=set(['B']), weight=3)
C_1 = HuffmanTreeNode(symbols=set(['C']), weight=1)
D_1 = HuffmanTreeNode(symbols=set(['D']), weight=1)
E_1 = HuffmanTreeNode(symbols=set(['E']), weight=1)
F_1 = HuffmanTreeNode(symbols=set(['F']), weight=1)
G_1 = HuffmanTreeNode(symbols=set(['G']), weight=1)
H_1 = HuffmanTreeNode(symbols=set(['H']), weight=1)
### Merging G1 and H1
GH_2 = HuffmanTree.mergeTwoNodes(G_1, H_1)
### Merging E_1 and F_1
EF_2 = HuffmanTree.mergeTwoNodes(E_1, F_1)
### Merging C_1 and D_1
CD_2 = HuffmanTree.mergeTwoNodes(C_1, D_1)
### Merging EF_2 and GH_2
EFGH_4 = HuffmanTree.mergeTwoNodes(EF_2, GH_2)
### Merging B_3 and CD_2
BCD_5 = HuffmanTree.mergeTwoNodes(B_3, CD_2)
### Merging BCD_5 and EFGH_4
BCDEFGH_9 = HuffmanTree.mergeTwoNodes(BCD_5, EFGH_4)
### Merging A_8 and BCDEFGH_9
ABCDEFGH_17 = HuffmanTree.mergeTwoNodes(A_8, BCDEFGH_9)
### Let's construct the first huffman tree
ht01 = HuffmanTree(root=ABCDEFGH_17)
### Let's encded and decode some two simple texts with ht01.
txt0 = 'AABCCDEFG'
txt1 = 'AABBBBHHHFFGGGDDDDEEEECCCCCCCCDDDDDHHHHHAAAAAA'
enc0 = ht01.encodeText(txt0)
print('txt0 = {}'.format(txt0))
print('enc0 = {}'.format(enc0))
enc1 = ht01.encodeText(txt1)
print('txt1 = {}'.format(txt1))
print('enc1 = {}'.format(enc1))
dec0 = ht01.decode(enc0)
print('dec0 = {}'.format(dec0))
# dec1 = ht01.decode(enc1)
# print('dec1 = {}'.format(dec1)) 