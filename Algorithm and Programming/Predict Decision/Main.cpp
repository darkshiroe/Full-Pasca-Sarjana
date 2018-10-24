#include <stdio.h>
void checkPredicate() {
	char predicate;
	int value;

	printf("Silahkan Masukan Nilai : ");
	scanf("%d", &value);

	predicate = (
		(value > 80 && value <= 100) ? printf("A") :
		(value <= 80 && value > 75) ? printf("B") :
		(value <= 75 && value > 65) ? printf("C") :
		(value <= 65 && value > 55) ? printf("D") :
		(value <= 55 && value > 0) ? printf("E") : printf("Cannot Be Predicated")
		);
}

void kabisatYear() {
	int year, result;
	printf("Please Input Year To Decide? ");
	scanf("%d", &year);

	if ((year % 400 == 0) && (year % 100 != 0)) {
		result = 1;
	}
	else if ((year % 100 == 0)) {
		result = 0;
	}
	else if ((year % 4 == 0)) {
		result = 1;
	}
	else {
		result = 0;
	}

	if (result == 1) {
		printf("Tahun %d Yang Di Isikan Adalah Tahun Kabisat", year);
	}
	else {
		printf("Tahun %d Yang Di Isikan Adalah Bukan Tahun Kabisat", year);
	}
}


void checkBigvalue() {
	int Firstvalue, Secondvalue, ThirdValue;

	printf("Silahkan Masukan Nilai Pertama : ");
	scanf("%d", &Firstvalue);
	printf("Silahkan Masukan Nilai Kedua : ");
	scanf("%d", &Secondvalue);
	printf("Silahkan Masukan Nilai Ketiga : ");
	scanf("%d", &ThirdValue);

	if ((Firstvalue > Secondvalue) && (Firstvalue > ThirdValue)) {
		printf("NIlai Terbesar %d", Firstvalue);
	}
	else if (Secondvalue > ThirdValue) {
		printf("NIlai Terbesar %d", Secondvalue);
	}
	else {
		printf("NIlai Terbesar %d", ThirdValue);
	}
}


void nestedIf() {
	char predicated;
	int value;

	printf("Silahkan Masukan Nilai : ");
	scanf("%d", &value);

	if (value > 80 && value <= 100) {
		printf("A");
	}
	else {
		if (value <= 80 && value > 75) {
			printf("B");
		}
		else {
			if (value <= 75 && value > 65) {
				printf("C");
			}
			else {
				if (value <= 65 && value > 55) {
					printf("D");
				}
				else {
					if (value <= 55 && value >= 0) {
						printf("E");
					}
					else {
						printf("Cannot Be Predicated");
					}
				}
			}
		}
	}
}



int main() {
	int selected;
	printf("1. Fungsi Penentuan Predkiat Nilai \n");
	printf("2. Fungsi Mencari Data Terbesar \n");
	printf("3. Nested If \n");
	printf("4. Kabisat Year Decide \n");
	printf("Silahkan Memilih Fungsi Yang Akan Di Gunakan : ");
	scanf("%d", &selected);

	if (selected == 1) {
		checkPredicate();
	}

	if (selected == 2) {
		checkBigvalue();
	}

	if (selected == 3) {
		nestedIf();
	}

	if (selected == 4) {
		kabisatYear();
	}

	return 1;
}