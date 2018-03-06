/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wrongLetters = 0;

function changeImg(){
    
    wrongLetters++;
    if(wrongLetters === 1){
        document.getElementById("galgeImg").src = "Images/galge1.png";
    }
        if(wrongLetters === 2){
        document.getElementById("galgeImg").src = "Images/galge2.png";
    }
        if(wrongLetters === 3){
        document.getElementById("galgeImg").src = "Images/galge3.png";
    }
        if(wrongLetters === 4){
        document.getElementById("galgeImg").src = "Images/galge4.png";
    }
        if(wrongLetters === 5){
        document.getElementById("galgeImg").src = "Images/galge5.png";
    }
        if(wrongLetters === 6){
        document.getElementById("galgeImg").src = "Images/galge6.png";
        wrongletters == 0;
    }
        if(wrongLetters === 0){
        document.getElementById("galgeImg").src = "Images/galge.png";
    }
}