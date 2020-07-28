import MenuOption

# Class used to build our Menu object. The menu will take a header argument which is displayed before each menu option to give a description of the menu.
class Menu():
    def __init__(self, header):
        self.__m_header = header
        self.__m_optionCount = 0
        self.__m_options = []

    # Add a menu option to our menu
    def addOption(self, command, description):
        if command is not None and command != "":
            self.__m_options.append(MenuOption.MenuOption(command, description))
            self.__m_optionCount += 1

    # Method used to confirm that the value provided is valid.
    def __isValidCommand(self, command):
        isValid = False
        if command == "X":
            isValid = True
        else:
            for i in range(self.getOptionCount()):
                if command == self.getOption(i).getCommand():
                    isValid = True
                    break
        return isValid

    # Method used to get an option at an index value
    def getOption(self, optionIndex):
        option = None
        if optionIndex >= 0 and optionIndex < self.getOptionCount():
            option = self.__m_options[optionIndex]
        return option

    # Getter method to return our header
    def getHeader(self):
        return self.__m_header

    # Getting method to get a count of options
    def getOptionCount(self):
        return self.__m_optionCount

    # Method to show our menu
    def show(self):
        command, keepGoing = '', True

        while keepGoing:
            optionList = ''

            print()
            print(self.getHeader(), "menu:")

            for i in range(self.getOptionCount()):
                option = self.getOption(i)
                if option is not None:
                    # 1st field is 6 chars wide
                    print(f"{option.getCommand()} - {option.getDescription()}")
                    optionList += option.getCommand() + ", "

            print("X - Exit")
            optionList += "X"

            print(f"\nEnter a {self.getHeader()} command ({optionList})")
            command = input()
            keepGoing = not self.__isValidCommand(command)

        return command
