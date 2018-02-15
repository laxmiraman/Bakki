'# XML Tool' 

This tool removes the redundant Headword in the Inflection

Example:

Input_dictionary.xml has the duplicates as below

<Head>
      <Word>
        <Key>A</Key>
        <Phonetic type="IPA">eɪ</Phonetic>
        <Inflections>
            <Inflection><Word><Key>a</Key></Word></Inflection>
            <Inflection><Word><Key>A</Key></Word></Inflection>
            <Inflection><Word><Key>A's</Key></Word></Inflection>            
        </Inflections>       
      </Word>
</Head>

Output.xml will be generated as,

<Head>
      <Word>
        <Key>A</Key>
        <Phonetic type="IPA">eɪ</Phonetic>
        <Inflections>
            <Inflection><Word><Key>a</Key></Word></Inflection>            
            <Inflection><Word><Key>A's</Key></Word></Inflection>            
        </Inflections>       
      </Word>
</Head>