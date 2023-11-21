USE `base`;

INSERT INTO base.sys_resource (id, resource_code, resource_name, resource_type, resource_parent, resource_status,
                               resource_icon, resource_path, resource_order)
VALUES (1, 'home', '首页', 'MENU', 0, 1, '', './cache-config.html', 0);
INSERT INTO base.sys_resource (id, resource_code, resource_name, resource_type, resource_parent, resource_status,
                               resource_icon, resource_path, resource_order)
VALUES (2, 'config', '配置管理', 'MENU', 0, 1, '', '', 1);
INSERT INTO base.sys_resource (id, resource_code, resource_name, resource_type, resource_parent, resource_status,
                               resource_icon, resource_path, resource_order)
VALUES (3, 'system', '系统管理', 'MENU', 0, 1, '', '', 2);
INSERT INTO base.sys_resource (id, resource_code, resource_name, resource_type, resource_parent, resource_status,
                               resource_icon, resource_path, resource_order)
VALUES (4, 'cache', '缓存配置', 'MENU', 2, 1, '', './cache-config.html', 0);

INSERT INTO base.sys_role (id, role_code, role_name, role_status)
VALUES (1, 'admin', '管理员', 1);

INSERT INTO base.sys_role_resource (id, role_id, resource_id)
VALUES (1, 1, 1);
INSERT INTO base.sys_role_resource (id, role_id, resource_id)
VALUES (2, 1, 2);
INSERT INTO base.sys_role_resource (id, role_id, resource_id)
VALUES (3, 1, 3);
INSERT INTO base.sys_role_resource (id, role_id, resource_id)
VALUES (4, 1, 4);

INSERT INTO base.sys_user (id, account_name, user_name, user_password, user_status)
VALUES (1, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);

INSERT INTO base.sys_user_role (id, user_id, role_id)
VALUES (1, 1, 1);
