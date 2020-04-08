#L(M1-18): {w | w contains the substring '00' or '11'}

def problem_1_18(test):

    #Defining required variables and methods
    
    acceptable = None #boolean value: returns true if it is acceptable, false otherwise
    i = 0 #our incrementor variable

    #This is the initial state, represented by q0
    def q0(test):
        nonlocal i
        nonlocal acceptable
        acceptable = False
        if len(test) > 0:
            if test[i:i+1] == "0":
                q1(test[i+1:])
            else: #assuming char is '1'
                q2(test[i+1:])
                
    def q1(test):
        nonlocal i
        nonlocal acceptable
        acceptable = False
        if len(test) > 0:
            if test[i:i+1] == "0":
                q3(test[i+1:])
            else: #assuming char is '1'
                q2(test[i+1:])
                
    def q2(test):
        nonlocal i
        nonlocal acceptable
        acceptable = False
        if len(test) > 0:
            if test[i:i+1] == "0":
                q1(test[i+1:])
            else: #assuming char is '1'
                q3(test[i+1:])
                        
    def q3(test):
        nonlocal i
        nonlocal acceptable
        acceptable = True
        if len(test) > 0:
            if test[i:i+1] == "0" or test[i:i+1] == "1":
                q3(test[i+1:])

    ##########################################################################################################

    #start of FA. Begins at q0, the initial state      

    q0(test)

    #Returns the boolean value (Acceptable -> True, Rejectable -> False)
    return acceptable


    ###########################################################################################################

if __name__ == "__main__":
    
    #Creating Test Cases for the Finite Automata
    
    test1 = "00"            #acceptable
    test2 = "01010101"      #rejectable
    test3 = ""              #rejectable
    test4 = "10101"         #rejectable
    test5 = "101011010"     #acceptable
    test6 = "11"            #acceptable

    print("Test Case 1: \"00\"", problem_1_18(test1),'', sep="\n")
    print("Test Case 2: \"01010101\"", problem_1_18(test2),'', sep="\n")
    print("Test Case 3: \"\"", problem_1_18(test3),'', sep="\n")
    print("Test Case 4: \"10101\"", problem_1_18(test4),'', sep="\n")
    print("Test Case 5: \"101011010\"", problem_1_18(test5),'', sep="\n")
    print("Test Case 6: \"11\"", problem_1_18(test6),'', sep="\n")
