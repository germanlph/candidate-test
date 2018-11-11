import { FormControl } from '@angular/forms';

export function ValidPhone(c: FormControl) {
    let phoneRegEx = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]\d{3}[\s.-]\d{4}$/;

    return phoneRegEx.test(c.value) ? null : {
        validPhone: {
            valid: false
        }
    };
}
