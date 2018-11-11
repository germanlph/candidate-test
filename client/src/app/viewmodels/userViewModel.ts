export class UserViewModel {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    validationErrorMap: UserProfileErrors;
}

export class UserProfileErrors {
    userId: string;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    firstNameValid: string;
    lastNameValid: string;
    emailValid: string;
    emailDuplicate: string;
}