package com.eminence.webservices.stub;

import javax.servlet.http.HttpServletRequest;

class TeamCityStub implements ServiceStub {
    private static final String USERS_URI ="/app/rest/users";
    private static final String USER_URI ="/app/rest/users/{userLocator}";//GET=serveUser,DELETE=deleteUser,PUT=updateUser
    private static final String USER_FIELD_URI ="/app/rest/users/{userLocator}/{field}";//GET=serveUserField,PUT=setUserField
    private static final String USER_PROPERTIES_URI ="/app/rest/users/{userLocator}/properties";//GET=serveUserProperties
    private static final String USER_PROPERTY_URI ="/app/rest/users/{userLocator}/properties/{name}";//GET=serveUserProperty,PUT=putUserProperty,DELETE=removeUserProperty
    private static final String USER_ROLES_URI ="/app/rest/users/{userLocator}/roles";//GET=listRoles,PUT=replaceRoles,POST=addRole
    private static final String USER_ROLE_URI ="/app/rest/users/{userLocator}/roles/{roleId}/{scope}";//GET=listRole,PUT=addRoleSimple,POST=addRoleSimplePost,DELETE=deleteRole
    private static final String USER_GROUPS_URI ="/app/rest/users/{userLocator}/groups";//GET=getGroups,PUT=replaceGroups,POST=addGroup

    public String getResponseFor(HttpServletRequest httpServletRequest) {
        return null;
    }

}
