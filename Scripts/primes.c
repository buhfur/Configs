
/* Author: Britton Wolfe */
/* Date: July 16, 2009 */
/* This program outputs all the primes in the range given */
/* by the command line arguments */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main(int argc, const char** argv){

	int lowerBound, upperBound;// what do these do ?
	//makes sure the user entered at least 2 arguments 
	if(argc != 3){
		fprintf(stderr, "USAGE: %s lowerBound upperBound\n", argv[0]);
		return -1;
	}

	lowerBound = atoi(argv[1]); // converts string argument from user into an integer 
	upperBound = atoi(argv[2]); 

	if(lowerBound < 1 || upperBound < 1){ //makes sure the entered integer is a non-zero value 
		fprintf(stderr, "ERROR: both the lowerBound (%d) and the upperBound (%d)"
				" must be positive.\n", lowerBound, upperBound);
		return -2;
	}

	{

		/* TODO: fill in the code that outputs the prime numbers */
		/*   in the range [lowerBound,upperBound] in ascending order */


		/* TODO: test this code with primesCheckOutput and runSimpleTests.sh*/
		// TODO: problem when running 1 and 2 as input 
		// TODO: use the sieve of eratosthenes algorithm 

		
		for( int i = lowerBound ; i <= upperBound ; ++i){ 
			int isPrime = 1; //variable that dictates if the current integer being acted on is prime 

			for( int j = 2; j <= i / j ; j += (j & 1) + 1){

				if(i % j == 0 ){ //if returns 1 , then the number is not prime 

					//TODO: replace with: "isPrime = 1; " if statment below doesnt work 
					isPrime = 0;  
					break;
				}
			}
			//determine if the number is prime 
			if(isPrime && i > 1){ 
				printf("%d\n",i); // prints out all prime numbers found 
			}
		}






	}

	return 0;
}
