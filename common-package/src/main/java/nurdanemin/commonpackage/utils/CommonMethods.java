package nurdanemin.commonpackage.utils;

import nurdanemin.commonpackage.events.Id;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommonMethods {
    public static Set<UUID> getItemsAsUUIDSet(Set<? extends Id> items){
        Set<UUID> response = new HashSet<>();
        for (var item :items ){
            response.add(item.getId());
        }
        return response;
    }
}
