import csv
from importlib.metadata import entry_points
import math
import copy
# from turtle import position

from attr import attr

##################################################
# module: bin_id3.py
# description: Binary ID3 decision tree learning
# Stephen Beckstrand
# A02311346
# bugs to vladimir kulyukin on canvas
###################################################

### Positive and Negative Constant labels; don't change
### these.
PLUS  = 'Yes'
MINUS = 'No'

class id3_node(object):

    def __init__(self, lbl):
        self.__label = lbl
        self.__children = {}

    def set_label(self, lbl):
        self.__label = lbl
        
    def add_child(self, attrib_val, node):
        self.__children[attrib_val] = node

    def get_label(self):
        return self.__label

    def get_children(self):
        return self.__children

    def get_child(self, attrib_val):
        assert attrib_val in self.__children
        return self.__children[attrib_val]

class bin_id3(object):

    @staticmethod
    def get_attrib_values(a, kvt):
        """
        Looks up values of attribute a in key-value table.
        """
        return kvt[a]

    @staticmethod
    def get_example_attrib_val(example, attrib):
        """
        Get the value of attribute attrib in example.
        """
        assert attrib in example
        return example[attrib]

    @staticmethod
    def parse_csv_file_into_examples(csv_fp):
        """
        Takes a csv file specified by the path csv_fp and
        converts it into an array of examples, each of which
        is a dictionary of key-value pairs where keys are
        column names and the values are column attributes.
        """
        examples = []
        with open(csv_fp) as csv_file:    
            csv_reader = csv.reader(csv_file, delimiter=',')
            line_count = 0
            key_names  = None
            for row in csv_reader:
                if len(row) == 0:
                    continue
                if line_count == 0:
                    key_names = row
                    for i in range(len(key_names)):
                        ## strip whitespace on both ends.
                        row[i] = row[i].strip()
                        line_count += 1
                else:
                    ex = {}
                    for i, k in enumerate(key_names):
                        ## strip white spaces on both ends.
                        ex[k] = row[i].strip()
                    examples.append(ex)
            return examples, key_names

    @staticmethod
    def construct_attrib_values_from_examples(examples, attributes):
        """
        Constructs a dictionary from a list of examples where each attribute
        is mapped to a list of all its possible values in examples.
        """
        avt = {}
        for a in attributes:
            if not a in avt:
                avt[a] = set()
            for ex in examples:
                if a in ex:
                    if not ex[a] in avt[a]:
                        avt[a].add(ex[a])
                else:
                    avt[a].add(None)
        return avt

    @staticmethod
    def find_examples_given_attrib_val(examples, attrib, val):
        """
        Finds all examples in such that attrib = val.
        """
        rslt = []
        #print('Looking for examples where {}={}'.format(attrib, val))
        for ex in examples:
            if attrib in ex:
                if ex[attrib] == val:
                    rslt.append(ex)
        return rslt

    @staticmethod
    def find_most_common_attrib_val(examples, attrib, avt):
        """
        Finds the most common value of attribute attrib in examples.
        """
        attrib_vals = bin_id3.get_attrib_values(attrib, avt)
        val_counts = {}
        for av in attrib_vals:
            SV = bin_id3.find_examples_given_attrib_val(examples, attrib, av)
            val_counts[av] = len(SV)
        max_cnt = 0
        max_val = None
        #print('val_counts = {}'.format(val_counts))
        for val, cnt in val_counts.items():
            if cnt > max_cnt:
                max_cnt = cnt
                max_val = val
        assert max_val != None
        return max_val, max_cnt

    @staticmethod
    def get_non_target_attributes(target_attrib, attribs):
        """
        Returns a comma separated string of all attributes in the list attribs that
        that are not equal to target_attrib; 
        - target_attrib is a string.
        - attribs is a list of strings.
        """
        return ', '.join([a for a in attribs if a != target_attrib])

    @staticmethod
    def display_info_gains(gains):
        """
        Displays a dictionary of information gains in the format attribute: gain.
        """
        print('Information gains are as follows:')
        for attrib, gain in gains.items():
            print('\t{}: {}'.format(attrib, gain))

    @staticmethod
    def display_id3_node(node, tabs):
        """
        Displays the subtree rooted at a node.
        """
        print(tabs + '{}'.format(node.get_label()))
        children = node.get_children()
        for v, n in children.items():
            print(tabs + '\t{}'.format(v))
            bin_id3.display_id3_node(n, tabs+'\t\t')

    @staticmethod
    def proportion(examples, attrib, val):
        """
        Computes the proportion of examples whose attribute attrib has the value val.
        """
        total = len(examples)
        if total == 0:
            return 0

        count = 0
        for i in examples:
            if i[attrib] == val:
                count += 1

        prop = count / total

        return prop

    @staticmethod
    def entropy(examples, attrib, avt):
        """
        Computes entropy of examples with respect of attribute attrib.
        avt is the attribute value table computed by construct_attrib_values_from_examples().
        """

        p = []
        val_set = list(avt[attrib])
        for i in range(0, len(val_set)):
            prop = bin_id3.proportion(examples, attrib, val_set[i])
            p.append(prop)

        entropy = 0
        for prop in p:
            if prop != 0:  
                entropy += (-1) * (prop) * math.log2(prop)

        return entropy
        
   
    @staticmethod
    def gain(examples, target_attrib, attrib, avt):
        """
        Computes gain of the attribute attrib in examples.
        """
        
        entropy = bin_id3.entropy(examples,target_attrib, avt)

        val_set = list(avt[attrib])

        gain_list = []
        difference = 0
        for val in val_set:
            sub_list = []
            for example in examples:
                if example[attrib] == val:
                    sub_list.append(example)
            gain_list.append(sub_list)
            

        difference = 0
        for sub_list in gain_list:
            prop = len(sub_list) / len(examples)
            ent = bin_id3.entropy(sub_list, target_attrib, avt)
            difference += prop * ent
        
        gain = entropy - difference

        return gain



   
    @staticmethod
    def find_best_attribute(examples, target_attrib, attribs, avt):
        """
        Finds the attribute in attribs with the highest information gain.
        This method returns three values: best attribute, its gain, and
        a dictionary that maps each attribute to its gain.
        """

        ba = ""
        bag = 0
        table = {}
        for attrib in attribs:
            if attrib != target_attrib:
                gain = bin_id3.gain(examples, target_attrib, attrib, avt)
                if gain > bag:
                    bag = gain
                    ba = attrib
                
                table[attrib] = gain

        return ba, bag, table


        

    @staticmethod
    def fit(examples, target_attrib, attribs, avt, dbg):
        """
        Returns a decision tree from examples given target_attribute target_attrib,
        attributes attribs, and attribute-value table.
        - examples is a list of examples;
        - target_attrib is a string (e.g., 'PlayTennis')
        - attribs is a list of attributes (strings)
        - avt is a dictionary constructed by construct_attrib_values_from_examples()
        - dbg is a debug flag True/False. When it is true, then things should
          be printed out as the algorithm computes the decision tree. For example,
          in my implementation I have things like
          if len(SV) == len(examples):
            ## if all examples are positive, then return the root node whose label is PLUS.
            if dbg:
                print('All examples positive...')
                print('Setting label of root to {}'.format(PLUS))
            root.set_label(PLUS)
            return root
        """
        global PLUS
        global MINUS
        
        if dbg:
            print("--- Next Run ---")

        try:
            attribs.remove(target_attrib)
        except:
            pass

        root = id3_node("")

        pos_examples = []
        neg_examples = []

        for example in examples: 
            if example[target_attrib] == PLUS:
                pos_examples.append(example)
            else:
                neg_examples.append(example)
        
        
        if len(pos_examples) == len(examples):
            root.set_label(PLUS)
            return root
        elif len(neg_examples) == len(examples):
            root.set_label(MINUS)
            return root
        

        if len(attribs) == 0:
            
            root.set_label(PLUS if pos_examples > neg_examples else MINUS) 
            if dbg:
                print("No more attributes. Setting root label to %s" % root.get_label)
            return root

        if dbg:
            print("looking for best attribute among: ")
            print(attribs)

        best_attrib, bag, table = bin_id3.find_best_attribute(examples,target_attrib,attribs, avt)
        root.set_label(best_attrib)

        if dbg:
            print("Details:")
            print(table)
            print("Best Attirubte: %s" % best_attrib)
            

        attribs_copy = copy.copy(attribs)
        attribs_copy.remove(best_attrib)
        
        if dbg:
            print("Removing %s from list of attirubtes" % best_attrib)


        for val in list(avt[best_attrib]):

            if dbg: 
                print("\nlooking for examples where %s=%s" % (best_attrib, val))

            sub_list = []
            for example in examples:
                if example[best_attrib] == val:
                    sub_list.append(example)

            if dbg: 
                print("Found the following examples where %s=%s" % (best_attrib, val))
                for i in sub_list:
                    print(i)

            if len(sub_list) == 0:
                root.set_label(PLUS if pos_examples > neg_examples else MINUS)
                child_node = id3_node(best_attrib)
                root.add_child(val, child_node)
            else:
                if dbg:
                    print("\nRecursively building decision tree for examples where %s=%s" % (best_attrib, val))

                child_node = bin_id3.fit(sub_list, target_attrib, attribs_copy, avt, True)
                root.add_child(val, child_node)
        
        return root
        



        
        



        


        # 1) Check if entropy of our set is 0. If so, all target values are the same. If yes, set root label to yes. If no, set root label to no. 
        # 2) Check the gain of all included attributes
        # 3) Split up up the intial set into sets for each value of the highest gain attribute. Example: Outlook will have the highest gain initially. Split dataset into three parts where Outlook = {sunny, overcast, rain}
        # 4) Remove attribute used to split from the set of attributes
        # 5) Recursively build a DT for each split set


                   
    @staticmethod
    def predict(root, example):
        """
        Classifies an example given a decision tree whose root is root.
        """
        global PLUS
        global MINUS
        
        label = root.get_label()
        if label == PLUS:
            return PLUS
        if label == MINUS:
            return MINUS
        
        rav = bin_id3.get_example_attrib_val(example, label)
        child = root.get_child(rav)
        return bin_id3.predict(child, example)