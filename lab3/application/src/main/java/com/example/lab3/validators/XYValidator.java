package com.example.lab3.validators;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

@Named(value="xYValidator")
@ApplicationScoped
public class XYValidator implements Validator<Float> {
    Float minValue = -5f;
    Float maxValue = 5f;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Float aFloat) throws ValidatorException {
        Float model = aFloat;
        if (model<minValue || model>maxValue){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Value must be from -5 to 5!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("Exception");
        }
    }
}
