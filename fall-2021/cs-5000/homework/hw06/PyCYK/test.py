from cyk import CYK
from cnfg import CNFG

gram = CNFG()

gram.clear_productions()
gram = CNFG()
gram.add_production("S", "A", "B")
gram.add_production("S", "B", "C")
gram.add_production("A", "B", "A")
gram.add_production("A", "a")
gram.add_production("B", "C", "C")
gram.add_production("B", "b")
gram.add_production("C", "A", "B")
gram.add_production("C", "a")

test_cyk = CYK()

print(test_cyk.is_in_cfl("baab", gram, True))
