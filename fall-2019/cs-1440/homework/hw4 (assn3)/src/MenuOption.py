
# Class used to build a single Menu Option object
class MenuOption():
    def __init__(self, command, description):
        self.__mo_command = command
        self.__mo_description = description

    # Method used to get our command value for Menu Option object
    def getCommand(self):
        return self.__mo_command

    # Getter method to get description of Menu Option object
    def getDescription(self):
        return self.__mo_description
