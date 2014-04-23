package jsf;

import entities.Userroles;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("RoleConverter")
public class RoleConverter implements Converter {
   @Override
   public Object getAsObject(FacesContext facesContext,
      UIComponent component, String value) {
      Userroles role = new Userroles(Integer.parseInt(value)); 
      return role;
   }

   @Override
   public String getAsString(FacesContext facesContext, 
      UIComponent component, Object value) {
      return value.toString();
   }
}