# String-From-Grammar-Generator
Application generates strings using the formal grammar given by user.


## Run application (Linux only)
> gradle run


## Examples:
`

    L(G) = {a^n b^n c^n | n≥1}
    
    G(N,𝜮,P,S)
    
    𝜮={a,b,c}
    
    N={S,B}
    
    S=S
    
    P={
    
        S → aBSc | abc
        
        Ba → aB
        
        Bb → bb
        
    }
    
`
