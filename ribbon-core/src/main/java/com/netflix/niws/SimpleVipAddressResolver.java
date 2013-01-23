/*
*
* Copyright 2013 Netflix, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/
package com.netflix.niws;

import java.util.regex.Pattern;

import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.IClientConfig;

public class SimpleVipAddressResolver implements VipAddressResolver {

    private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{(.*?)\\}");

    @Override
    public String resolve(String vipAddressMacro, IClientConfig niwsClientConfig) {
        if (vipAddressMacro == null || vipAddressMacro.length() == 0) {
            return vipAddressMacro;
        }
        if (!VAR_PATTERN.matcher(vipAddressMacro).matches()) {
            return vipAddressMacro;
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String address: vipAddressMacro.split(",")) {
            if (!first) {
                sb.append(",");
            }
            String interpolated = ConfigurationManager.getConfigInstance().getString(address);
            if (interpolated != null) {
                sb.append(interpolated);
            }
            first = false;
        }
        return sb.toString();
    }
}
