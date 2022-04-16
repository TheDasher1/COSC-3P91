package KnockKnock;


/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

    @modified Kyle Robert Harrison
    Replaced constants with an enumeration

 */

public class KnockKnockProtocol {
    private static final int NUMJOKES = 5;
 
    private KnockKnockState state = KnockKnockState.WAITING;
    private int currentJoke = 0;
 
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };
 
    public String processInput(String theInput) {
        String theOutput = null;
 
        switch(state){
            case WAITING: 
            theOutput = "Knock! Knock!";
            state = KnockKnockState.SENT_KNOCK_KNOCK;
            break;
        case SENT_KNOCK_KNOCK: 
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = KnockKnockState.SENT_CLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                "Try again. Knock! Knock!";
            }
            break;
        case SENT_CLUE:
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = KnockKnockState.ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
                clues[currentJoke] + 
                " who?\"" + 
                "! Try again. Knock! Knock!";
                state = KnockKnockState.SENT_KNOCK_KNOCK;
            }
            break;
        case ANOTHER:
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = KnockKnockState.SENT_KNOCK_KNOCK;
            } else {
                theOutput = "Bye.";
                state = KnockKnockState.WAITING;
            }
            break;
        }
    
        return theOutput;
    }
}