<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios';

const CodeResult = ref("");
const CodeArea = ref(""); //Bind textarea to this variable

async function compileCodeAndGetResult() {
    let result;
    try {
        //TODO change adress
        let apiResponse = await axios.post("http://localhost:8080/compileAndRun", {
            "code" : CodeArea.value
        });
        console.log(apiResponse);
        result = apiResponse.data["result"];
    } catch (error) {
        //Maybe add error message to result here
        result = "server not able to be contacted";
    }
    CodeResult.value = result;
}
</script>

<template>

    <div id="wrapper">
        <div id="codeWritingSection">
            <h1>Enter your code here</h1>
            <textarea v-model="CodeArea" id="Code"></textarea>
            <button id="CompileButton" @click="compileCodeAndGetResult">Compile and run</button>
        </div>

        <div id="codeResultSection">
            <h1>Output</h1>
            <p id="CodeResult" >{{ CodeResult }}</p>
        </div>
    </div>
</template>

<style>
#CompileButton {
    background-color: blue;
    color: white;
}

#wrapper {
  display: flex;
    gap: 20px; /* Space between sections */
    align-items: flex-start; /* Align elements to the top */
}

#codeWritingSection {
    flex: 1;
}

#codeResultSection {
    flex: 1;
}

#CodeResult {
    width: 500px;
    height: 500px;
    border: 3px solid black;
    white-space: pre-line;
}

#Code {
    width: 500px;
    height: 100px;
}
</style>
