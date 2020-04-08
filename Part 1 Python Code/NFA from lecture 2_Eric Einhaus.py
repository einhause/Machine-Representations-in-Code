#Eric Einhaus
#Computer Algorithms
#Nondeterinistic Finite Automata
#L(MNFA2): {w | w contains an odd number of 0's or a number of 1's not a multiple of 3 (both can apply)}

def MNFA2(test):

    #Defining required variables and methods
    ###########################################################################################################
    
    i = 0 #our incrementor variable

    #Pointer boolean values. True if a pointer is at the state.
    pq0 = False
    pq1 = False
    pq2 = False
    pq3 = False
    pq4 = False
    pq5 = False

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
            if test[i:i+1] == "0":
                pq1 = False
                q3(test[i+1:])
            else: #'1'
                q1(test[i+1:]) 
                
    def q2(test):
        nonlocal i
        nonlocal pq2
        pq2 =  True
        if len(test) > 0:
            if test[i:i+1] == "0":
                q2(test[i+1:])
            else: #'1'
                pq2 = False
                q4(test[i+1:])
                     
    def q3(test):
        nonlocal i
        nonlocal pq3
        pq3 = True
        if len(test) > 0:
            if test[i:i+1] == "0":
                pq3 = False
                q1(test[i+1:])
            else: #'1'
                q3(test[i+1:])
                

    def q4(test):
        nonlocal i
        nonlocal pq4
        pq4 = True
        if len(test) > 0:
            if test[i:i+1] == "0":
                q4(test[i+1:])
            else: #'1'
                pq4 = False
                q5(test[i+1:])

    def q5(test):
        nonlocal i
        nonlocal pq5
        pq5 = True
        if len(test) > 0:
            if test[i:i+1] == "0":
                q5(test[i+1:])
            else: #'1'
                pq5 = False
                q2(test[i+1:])
    

    ##########################################################################################################

    #start of NFA. Begins at q0, the initial state      

    q0(test)

    #Returns the boolean value (Acceptable -> True, Rejectable -> False)
    return (pq3 or pq4 or pq5)


    ###########################################################################################################

if __name__ == "__main__":
    
    #Creating Test Cases for the Finite Automata
    
    test1 = "001000" #acceptable
    test2 = "0011100" #rejectable
    test3 = ""  #rejectable
    test4 = "001111"  #acceptable
    test5 = "00111111" #rejectable
    test6 = "01010100" #acceptable
    test7 = "010101000" #rejectable
    test8 = "1010101" #acceptable

    print("Test Case 1: \"001000\"", MNFA2(test1),'', sep="\n")
    print("Test Case 2: \"0011100\"", MNFA2(test2),'', sep="\n")
    print("Test Case 3: \"\"", MNFA2(test3),'', sep="\n")
    print("Test Case 4: \"001111\"", MNFA2(test4),'', sep="\n")
    print("Test Case 5: \"00111111\"", MNFA2(test5),'', sep="\n")
    print("Test Case 6: \"01010100\"", MNFA2(test6),'', sep="\n")
    print("Test Case 7: \"010101000\"", MNFA2(test7),'', sep="\n")
    print("Test Case 8: \"1010101\"", MNFA2(test8),'', sep="\n")
