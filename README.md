# String-From-Grammar-Generator
Application generates strings using the formal grammar given by user.

Default parse strategy is **random**. 


## Run application (Linux only)
> gradle run


## Examples:

```
L(G) = {a^n b^n c^n | n≥1}
G(N,Ʃ,P,S)
Ʃ={a,b,c}
N={S,B}
S=S
P={
       S → aBSc | abc
       Ba → aB
       Bb → bb    
}
```

Generate Strings in **String-From-Grammar-Generator**:
```
+---------------------------------+
|  STRING FROM GRAMMAR GENERATOR  |
|          G = (N,Ʃ,P,S)          |
|                                 |
| N - nonterminal symbols         |
| Ʃ - terminal symbols            |
| P - production rules            |
| S - start symbol                |
+---------------------------------+
Set terminal symbols [Ʃ] (type "\next" to next step):
```
> a     _[enter]_ 

```Ʃ = {a}```

> b     _[enter]_

```Ʃ = {a,b}```

> c     _[enter]_

```Ʃ = {a,b,c}```

> \next     _[enter]_

```Set nonterminal symbols [N] (type "\next" to next step):```

> S     _[enter]_

```N = {S}```

> B     _[enter]_

```N = {S,B}```

> \next    _[enter]_

```Set start symbol [S]:```

> S     _[enter]_

```Set productions rules [P] (type "\next" to next step):
production key: ```

> S     _[enter]_

```production values (type "\next" to next production): 
S -> ```

> aBSc    _[enter]_

```production values (type "\next" to next production): 
S -> aBSc```

> abc    _[enter]_

```production values (type "\next" to next production): 
S -> aBSc | abc```

> \next    _[enter]_

```production key: ```

> Ba    _[enter]_

```S -> aBSc | abc
production values (type "\next" to next production): 
Ba -> ```

> aB    _[enter]_

```production values (type "\next" to next production): 
Ba -> aB```

> \next    _[enter]_

```production key: ```

> Bb    _[enter]_

```S -> aBSc | abc
Ba -> aB
production values (type "\next" to next production): 
Bb -> ```

> bb    _[enter]_

```production values (type "\next" to next production): 
Bb -> bb```

> \next    _[enter]_

```production key:```

> \next    _[enter]_

```Set number of generated strings: ```

> 3    _[enter]_

```------------------
 [3] aabbcc
 [6] aaabbbccc
 [3] abc
------------------```

In result: [X], X is the string length.





