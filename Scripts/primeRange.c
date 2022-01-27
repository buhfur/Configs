#include <stdio.h>


int main(){
	int lowerLevel, upperLevel;

	printf("Enter the lower limit and upper limit of the range: ");
	if (scanf("%d %d", &lowerLevel, &upperLevel) != 2) {
		return 1;
	}
	for (int i = lowerLevel; i <= upperLevel; ++i) {
		int isprime = 1;
		for (int p = 2; p <= i / p; p += (p & 1) + 1) {
			if (i % p == 0) {
				isprime = 0;
				break;
			}
		}
		if (isprime) {
			printf("%d ", i);
		}
	}
	printf("\n");
	return 0;
}


