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
        console.log(error);
    }
    CodeResult.value = result;
}
</script>

<template>

    <h1>Enter your code here</h1>
    <textarea v-model="CodeArea" id="Code"></textarea>

    <button id="CompileButton" @click="compileCodeAndGetResult">Compile and run</button>
    <h1>Output</h1>
    <p id="CodeResult" >{{ CodeResult }}</p>
</template>

<style>
#CompileButton {
    background-color: blue;
    color: white;
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
