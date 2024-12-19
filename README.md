This is a class responsible for checking violated constraints on entity field. If any constraint decleared on a field is violated, a message related to the violated constraint is sent to the client with details specific to the violated constraint.
To regster the validating class to a RESTful web servive runtime,it has to be annotated with the @Provider annotation.
