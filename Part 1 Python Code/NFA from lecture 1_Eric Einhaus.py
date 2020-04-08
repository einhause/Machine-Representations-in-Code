#Eric Einhaus
#Computer Algorithms
#Nondeterinistic Finite Automata
#L(MNFA1): {w | w contains all 'a's (including 0) or alternating 'ab' segments}

def MNFA1(test):

    #Defining required variables and methods
    ###########################################################################################################
    
    i = 0 #our incrementor variable

    #Pointer boolean values. True if a pointer is at the state.
    pq0 = False
    pq1 = False
    pq2 = False
    pq3 = False
    pq4 = False

    #This is the initial state, represented by q0
    def q0(test):
        nonlocal i
        nonlocal pq0
        pq0 = True
        q1(test) #Representing the Epsilon pointers
        q2(test) # "
        pq0 = False
                
    def q1(test):
        nonlocal i
        nonlocal pq1
        pq1 = True
        if len(test) > 0:
            if test[i:i+1] == "a":
                q1(test[i+1:])
            else:
                pq1 = False
                
    def q2(test):
        nonlocal i
        nonlocal pq2
        pq2 =  True
        if len(test) > 0:
            if test[i:i+1] == "a":
                q3(test[i+1:])
            else:
                pq2 = False
                      
    def q3(test):
        nonlocal i
        nonlocal pq3
        pq3 = True
        if len(test) > 0:
            if test[i:i+1] == "b":
                q4(test[i+1:])
            else:
                pq3 = False
                

    def q4(test):
        nonlocal i
        nonlocal pq4
        pq4 = True
        if len(test) > 0:
            pq4 = False
        q2(test) #Epsilon Pointer
    

    ##########################################################################################################

    #start of NFA. Begins at q0, the initial state      

    q0(test)

    #Returns the boolean value (Acceptable -> True, Rejectable -> False)
    return (pq1 or pq4)


    ###########################################################################################################

if __name__ == "__main__":
    
    #Creating Test Cases for the Finite Automata
    
    test1 = "aa" #acceptable
    test2 = "aba" #rejectable
    test3 = ""  #acceptable
    test4 = "abbab"  #rejectable
    test5 = "abababab" #acceptable
    test6 = "baba" #rejectable

    print("Test Case 1: \"aa\"", MNFA1(test1),'', sep="\n")
    print("Test Case 2: \"aba\"", MNFA1(test2),'', sep="\n")
    print("Test Case 3: \"\"", MNFA1(test3),'', sep="\n")
    print("Test Case 4: \"abbab\"", MNFA1(test4),'', sep="\n")
    print("Test Case 5: \"abababab\"", MNFA1(test5),'', sep="\n")
    print("Test Case 6: \"baba\"", MNFA1(test6),'', sep="\n")
