// Assignment 2 For Alogorithm and Programming
// 4 in 1 Function Include to 1 Main CPP
// Author	: Bayu Dwiyan Satria
// NRP		: 2020181005
// Program	: Teknik Informatika dan Komputer
// Politeknik Elektronika Negeri Surabaya


// Platform C++ Running on Visual Studio Entreprise 2017
#include <stdlib.h>
#include <stdio.h>

void introduction(); //Student Data
int menuSelection(); //Function Menu
void selectFunction(int select); //Feature Selection
void nToZero(int n); //Assignment No 1
void zeroToN(int n); //Assignment No 2
int checkMember(int arrayValues[], int valuesToCheck, int arrLimit); //Assignment No 3
void reverseSentence(char *str); //Assignment No 4

int main() {
	menuSelection();
}

void introduction() {
	printf("Alogritm and Programming Assignment 2 \n");
	printf("Recursion Function \n \n");
	printf("Name : Bayu Dwiyan Satria \n");
	printf("NRP : 2020181005 \n \n");
}

int menuSelection() {
	introduction();
	int select;
	printf("Please Select Function To Run: \n");
	printf("1. Function N to Zero \n");
	printf("2. Function Zero to N \n");
	printf("3. Function Check of Vlaues is a member of an Array \n");
	printf("4. Function Reverse of Sentence \n");
	printf("0. Exit Program \n \n");

	printf("Select : ");
	scanf("%d", &select);
	getchar();
	printf("%d Selected \n \n", select);


	do {
		selectFunction(select);
		break;
	} while (select != 0);

	return 0;

}

void selectFunction(int select) {

	if (select == 0) {
		printf("Thank You! \n");
		printf("Bayu Dwiyan Satria - 2020181005 \n");
		system("PAUSE");
	}

	if (select == 1) {
		int n;
		printf(" ============================================ Function N to Zero RUNNING ============================================ \n \n");
		printf("Please Input Value : \n");
		scanf("%d", &n);
		getchar();
		nToZero(n);
		printf(" \n ============================================ Function N to Zero END ============================================ \n \n");
		menuSelection();
	}

	if (select == 2) {
		int n;
		printf(" ============================================ Function Zero to N RUNNING ============================================ \n \n");
		printf("Please Input Value : \n");
		scanf("%d", &n);
		getchar();
		zeroToN(n);
		printf(" \n ============================================ Function Zero to N END ============================================ \n \n");
		menuSelection();
	}

	if (select == 3) {
		printf(" =============================== Function Check of Values is a member of an Array RUNNING ========================== \n \n");
		int* arrayValues = NULL;
		int arrLimit;
		printf("Please Input Total Value of Array : \n");
		scanf("%d", &arrLimit);
		getchar();
		int values;
		arrayValues = new int[arrLimit];
		for (int i = 0; i < arrLimit; i++) {
			values = rand() % arrLimit;
			arrayValues[i] = { values };
		}

		int valuesToCheck;
		printf("Please Input Value To Check : \n");
		scanf("%d", &valuesToCheck);
		getchar();

		printf("Function Running \n");

		printf("Array Values is Picked Random Between 0 and Total Input: \n");
		printf("Array Data :\n");

		for (int i = 0; i < arrLimit; i++) {
			printf("%d", arrayValues[i]);
			printf("\t");
		}

		printf("\n \n");

		int result;
		result = checkMember(arrayValues, valuesToCheck, arrLimit);

		printf("Result : \n");
		if (result == 1) {
			printf("Your Input %d Is a Member of an Array \n", valuesToCheck);
		}
		else {
			printf("Your Input %d Not a Member of an Array \n", valuesToCheck);
		}
		printf(" \n ======================== Function Check of Values is a member of an Array END ============================= \n \n");
		menuSelection();


	}

	if (select == 4) {

		printf(" ============================== Function Reverse of Sentence RUNNING ======================================= \n \n");
		char sentence[256];
		printf("Input a Sentences : \n");
		scanf("%[^\n]", &sentence);
		getchar();
		printf("Result : \n");
		reverseSentence(sentence);

		printf(" \n ============================== Function Reverse of Sentence END =========================================== \n \n");

		menuSelection();
	}

	if ((select > 4) || (select < 0)) {
		printf("No Data Available, Please Select one of number below : \n");
		menuSelection();
	}
}


//==================================================== START Function N to Zero ================================================================//

void nToZero(int n) {
	if (n < 0) return;
	printf("%d \t", n);
	nToZero(n - 1); //<-- Recrusive Here
}

//==================================================== END Function N to Zero ==================================================================//
//==================================================== START Function Zero to N ================================================================//

void zeroToN(int n) {
	static int i = 0;
	if (n < 0) return;
	printf("%d \t", i++);
	zeroToN(n - 1); //<-- Recrusive Here
}


//==================================================== END Function Zero to N ==================================================================//
//==================================================== START Function Check of X is a member of an Array =======================================//


int checkMember(int arrayValues[], int valuesToCheck, int arrLimit) {
	for (int i = 0; i < arrLimit;i++) {
		if (valuesToCheck == arrayValues[i]) {
			return 1;
		}
		else if (i == arrLimit) {
			return 0;
		}
		else {
			checkMember(arrayValues, valuesToCheck, arrLimit); //<-- Recrusive Here
		}
	}

}

//==================================================== END Function Check of X is a member of an Array ===================================//
//==================================================== START Function Reverse of Sentence ===============================================//


void reverseSentence(char *str) {
	if (*str) {
		reverseSentence(str + 1); //<-- Recrusive Here
		printf("%c", *str);
	}
}

//==================================================== END Function Reverse of Sentence =================================================//