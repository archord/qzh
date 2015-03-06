/*
Navicat PGSQL Data Transfer

Source Server         : pc780
Source Server Version : 90305
Source Host           : 10.36.1.154:5432
Source Database       : quanzheng
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90305
File Encoding         : 65001

Date: 2015-03-06 13:55:24
*/


-- ----------------------------
-- Sequence structure for cbdkxx_id_seq
-- ----------------------------
DROP SEQUENCE "cbdkxx_id_seq";
CREATE SEQUENCE "cbdkxx_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."cbdkxx_id_seq"', 4, true);

-- ----------------------------
-- Sequence structure for cbf_id_seq
-- ----------------------------
DROP SEQUENCE "cbf_id_seq";
CREATE SEQUENCE "cbf_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 16
 CACHE 1;
SELECT setval('"public"."cbf_id_seq"', 16, true);

-- ----------------------------
-- Sequence structure for cbf_jtcy_id_seq
-- ----------------------------
DROP SEQUENCE "cbf_jtcy_id_seq";
CREATE SEQUENCE "cbf_jtcy_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 17
 CACHE 1;
SELECT setval('"public"."cbf_jtcy_id_seq"', 17, true);

-- ----------------------------
-- Sequence structure for cbht_id_seq
-- ----------------------------
DROP SEQUENCE "cbht_id_seq";
CREATE SEQUENCE "cbht_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 11
 CACHE 1;
SELECT setval('"public"."cbht_id_seq"', 11, true);

-- ----------------------------
-- Sequence structure for cbjyqz_id_seq
-- ----------------------------
DROP SEQUENCE "cbjyqz_id_seq";
CREATE SEQUENCE "cbjyqz_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 8
 CACHE 1;
SELECT setval('"public"."cbjyqz_id_seq"', 8, true);

-- ----------------------------
-- Sequence structure for cbjyqz_qzbf_id_seq
-- ----------------------------
DROP SEQUENCE "cbjyqz_qzbf_id_seq";
CREATE SEQUENCE "cbjyqz_qzbf_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."cbjyqz_qzbf_id_seq"', 6, true);

-- ----------------------------
-- Sequence structure for cbjyqz_qzhf_id_seq
-- ----------------------------
DROP SEQUENCE "cbjyqz_qzhf_id_seq";
CREATE SEQUENCE "cbjyqz_qzhf_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."cbjyqz_qzhf_id_seq"', 6, true);

-- ----------------------------
-- Sequence structure for cbjyqz_qzzx_id_seq
-- ----------------------------
DROP SEQUENCE "cbjyqz_qzzx_id_seq";
CREATE SEQUENCE "cbjyqz_qzzx_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."cbjyqz_qzzx_id_seq"', 6, true);

-- ----------------------------
-- Sequence structure for cbjyqzdjb_id_seq
-- ----------------------------
DROP SEQUENCE "cbjyqzdjb_id_seq";
CREATE SEQUENCE "cbjyqzdjb_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 11
 CACHE 1;
SELECT setval('"public"."cbjyqzdjb_id_seq"', 11, true);

-- ----------------------------
-- Sequence structure for dk_id_seq
-- ----------------------------
DROP SEQUENCE "dk_id_seq";
CREATE SEQUENCE "dk_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 10
 CACHE 1;
SELECT setval('"public"."dk_id_seq"', 10, true);

-- ----------------------------
-- Sequence structure for fbf_id_seq
-- ----------------------------
DROP SEQUENCE "fbf_id_seq";
CREATE SEQUENCE "fbf_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 22
 CACHE 1;
SELECT setval('"public"."fbf_id_seq"', 22, true);

-- ----------------------------
-- Sequence structure for lzht_id_seq
-- ----------------------------
DROP SEQUENCE "lzht_id_seq";
CREATE SEQUENCE "lzht_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 10
 CACHE 1;
SELECT setval('"public"."lzht_id_seq"', 10, true);

-- ----------------------------
-- Sequence structure for org_id_seq
-- ----------------------------
DROP SEQUENCE "org_id_seq";
CREATE SEQUENCE "org_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 9
 CACHE 1;
SELECT setval('"public"."org_id_seq"', 9, true);

-- ----------------------------
-- Sequence structure for qslyzlfj_id_seq
-- ----------------------------
DROP SEQUENCE "qslyzlfj_id_seq";
CREATE SEQUENCE "qslyzlfj_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 5
 CACHE 1;
SELECT setval('"public"."qslyzlfj_id_seq"', 5, true);

-- ----------------------------
-- Sequence structure for tdlylx_id_seq
-- ----------------------------
DROP SEQUENCE "tdlylx_id_seq";
CREATE SEQUENCE "tdlylx_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 18
 CACHE 1;
SELECT setval('"public"."tdlylx_id_seq"', 18, true);

-- ----------------------------
-- Table structure for a_organization
-- ----------------------------
DROP TABLE IF EXISTS "a_organization";
CREATE TABLE "a_organization" (
"org_id" int8 DEFAULT nextval('org_id_seq'::regclass) NOT NULL,
"org_name" varchar(255) COLLATE "default",
"parent_id" int8 DEFAULT 0,
"is_deleted" bool DEFAULT false,
"org_coding" varchar(255) COLLATE "default",
"postal_code" varchar(255) COLLATE "default",
"auth_org_name" varchar(255) COLLATE "default",
"auth_people" varchar(255) COLLATE "default",
"auth_phone" varchar(255) COLLATE "default",
"fbf_name" varchar(255) COLLATE "default",
"fbf_legal_person" varchar(255) COLLATE "default",
"fbf_phone" varchar(255) COLLATE "default",
"fbf_address" varchar(255) COLLATE "default",
"org_member" varchar(255) COLLATE "default",
"region_type" int2,
"org_level" int2
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of a_organization
-- ----------------------------
BEGIN;
INSERT INTO "a_organization" VALUES ('250', '高阳县', '0', 't', 'HB2313', null, '', '', '', '高阳县委', '张春华', '', '', '张春华', null, '1');
INSERT INTO "a_organization" VALUES ('251', '浦口乡', '250', 'f', 'HB2313001', null, '', '', '', '浦口乡政府', '姜红军', '', '', '姜红军', null, '2');
INSERT INTO "a_organization" VALUES ('252', '南马村', '251', 'f', 'HB231300101', null, '', '', '', '', '', '', '', '', null, '3');
INSERT INTO "a_organization" VALUES ('253', '飞龙寨村', '251', 'f', 'HB231300102', null, '', '', '', '', '', '', '', '', null, '3');
INSERT INTO "a_organization" VALUES ('254', '一组', '252', 'f', 'HB23130010101', null, '', '', '', '', '', '', '', '', null, '4');
INSERT INTO "a_organization" VALUES ('255', '二组', '252', 'f', 'HB23130010102', null, '', '', '', '', '', '', '', '', null, '4');
INSERT INTO "a_organization" VALUES ('300', '高阳县', '0', 't', 'HB2313', null, '', '', '', '高阳县委', '张春华', '', '', '张春华', null, '1');
INSERT INTO "a_organization" VALUES ('301', '阿克苏市', '0', 'f', '652901', null, '', '', '', '', '', '', '', '', null, '1');
INSERT INTO "a_organization" VALUES ('302', '托普鲁克乡', '301', 'f', '652901202', null, '', '', '', '', '', '', '', '', null, '2');
INSERT INTO "a_organization" VALUES ('303', '尤喀克喀拉喀勒村', '302', 'f', '652901202200', null, '', '', '', '', '', '', '', '', null, '3');
INSERT INTO "a_organization" VALUES ('350', '托万克喀拉喀勒村', '302', 'f', '652901202201', null, '', '', '', '', '', '', '', '', null, '3');
INSERT INTO "a_organization" VALUES ('400', '村3', '302', 'f', '652901202202', null, '', '', '', '', '', '', '', '', null, '3');
INSERT INTO "a_organization" VALUES ('450', '尤喀克喀拉喀勒村', '0', 't', '652901202200', null, '1', '1', '1', '1', '1', '1', '1', '1', null, '3');
COMMIT;

-- ----------------------------
-- Table structure for a_user
-- ----------------------------
DROP TABLE IF EXISTS "a_user";
CREATE TABLE "a_user" (
"id" int4 NOT NULL,
"ip" varchar(255) COLLATE "default",
"qq" varchar(255) COLLATE "default",
"addr" varchar(255) COLLATE "default",
"email" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
"password" varchar(255) COLLATE "default",
"phone" varchar(255) COLLATE "default",
"reg_date" date,
"sex" bool
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of a_user
-- ----------------------------
BEGIN;
INSERT INTO "a_user" VALUES ('1', null, null, null, null, 'root', '123', null, null, 't');
COMMIT;

-- ----------------------------
-- Table structure for cbdkxx
-- ----------------------------
DROP TABLE IF EXISTS "cbdkxx";
CREATE TABLE "cbdkxx" (
"dkbm" char(22) COLLATE "default",
"fbfbm" char(14) COLLATE "default",
"cbfbm" char(18) COLLATE "default",
"cbjyqqdfs" char(3) COLLATE "default",
"htmj" float4,
"cbhtbm" char(18) COLLATE "default",
"lzhtbm" char(20) COLLATE "default",
"cbjyqzbm" char(19) COLLATE "default",
"id" int8 DEFAULT nextval('cbdkxx_id_seq'::regclass) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbdkxx
-- ----------------------------
BEGIN;
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '0');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '50');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0002', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '51');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '11010100100101101   ', '11010100100101001J ', '52');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '100');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0002', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '101');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '11010100100101101   ', '11010100100101001J ', '102');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '150');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0002', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '151');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '11010100100101101   ', '11010100100101001J ', '152');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '200');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0002', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '                    ', '11010100100101001J ', '201');
INSERT INTO "cbdkxx" VALUES ('11010100100101001J0001', '11010100100101', '11010100100101001 ', '110', '100', '11010100100101001J', '11010100100101101   ', '11010100100101001J ', '202');
COMMIT;

-- ----------------------------
-- Table structure for cbf
-- ----------------------------
DROP TABLE IF EXISTS "cbf";
CREATE TABLE "cbf" (
"cbfbm" char(18) COLLATE "default",
"cbflx" char(1) COLLATE "default",
"cbfmc" varchar(50) COLLATE "default",
"cbfzjlx" char(1) COLLATE "default",
"cbfzjhm" varchar(20) COLLATE "default",
"cbfdz" varchar(100) COLLATE "default",
"yzbm" char(6) COLLATE "default",
"lxdh" varchar(20) COLLATE "default",
"cbfcysl" int4,
"cbfdcrq" date,
"cbfdcy" varchar(50) COLLATE "default",
"cbfdcjs" varchar(300) COLLATE "default",
"gsjs" varchar(300) COLLATE "default",
"gsjsr" varchar(50) COLLATE "default",
"gsshrq" date,
"gsshr" varchar(50) COLLATE "default",
"id" int8 DEFAULT nextval('cbf_id_seq'::regclass) NOT NULL,
"org_id" int8,
"cbfxb" char(1) COLLATE "default",
"cbfmz" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbf
-- ----------------------------
BEGIN;
INSERT INTO "cbf" VALUES ('HB2313001CB001    ', '1', '何大壮', '1', '123456', '河北省高阳县浦口乡南马村', '223564', '123456', '3', '2014-09-08', '魏宗人', '资质合格', '资质合格', '马加强', '2014-09-08', '蔡瑞华', '52', '252', '1', '维族人');
INSERT INTO "cbf" VALUES ('HB2313001CB002    ', '1', '辛小明', '1', '123456', '河北省高阳县浦口乡南马村', '223564', '123456', '3', '2014-09-08', '魏宗人', '资质合格', '资质合格', '马加强', '2014-09-08', '蔡瑞华', '53', '252', '2', '维族');
INSERT INTO "cbf" VALUES ('11010100100101002 ', '1', '哈里克·长焕', '1', '652901195402128730', '托普鲁克乡尤克喀拉喀勒村', '843003', '110012', '1', '2013-10-31', '调查员2', '合格', '合格', '公示记事人2', '2013-10-31', '公示审核人2', '151', '0', '1', '维族');
INSERT INTO "cbf" VALUES ('11010100100101001 ', '1', '吐尔汗·莫拉', '1', '652901195402123427', '托普鲁克乡尤克喀拉喀勒村', '843003', '110011', '3', '2014-10-13', '调查员1', '合格', '合格', '公示记事人1', '2014-10-14', '公示审核人1', '152', '0', '1', '维族');
INSERT INTO "cbf" VALUES ('11010100100101002 ', '1', '哈里克·长焕', '1', '652901195402128730', '托普鲁克乡尤克喀拉喀勒村', '843003', '110012', '1', '2014-10-13', '调查员2', '合格', '合格', '公示记事人2', '2014-10-15', '公示审核人2', '651', '0', '1', '维族');
INSERT INTO "cbf" VALUES ('11010100100101001 ', '1', '吐尔汗·莫拉', '1', '652901195402123427', '托普鲁克乡尤克喀拉喀勒村', '843003', '110011', '3', '2014-10-13', '调查员1', '合格', '合格', '公示记事人1', '2014-10-14', '公示审核人1', '750', '0', '1', '维族');
INSERT INTO "cbf" VALUES ('11010100100101002 ', '1', '哈里克·长焕', '1', '652901195402128730', '托普鲁克乡尤克喀拉喀勒村', '843003', '110012', '1', '2014-10-13', '调查员2', '合格', '合格', '公示记事人2', '2014-10-15', '公示审核人2', '751', '0', '1', '维族');
INSERT INTO "cbf" VALUES ('11010100100101001 ', '1', '吐尔汗·莫拉', '1', '652901195402123427', '托普鲁克乡尤克喀拉喀勒村', '843003', '110011', '3', '2014-10-13', '调查员1', '合格', '合格', '公示记事人1', '2014-10-14', '公示审核人1', '800', '0', '1', '维族');
INSERT INTO "cbf" VALUES ('11010100100101002 ', '1', '哈里克·长焕', '1', '652901195402128730', '托普鲁克乡尤克喀拉喀勒村', '843003', '110012', '1', '2014-10-13', '调查员2', '合格', '合格', '公示记事人2', '2014-10-15', '公示审核人2', '801', '0', '1', '维族');
COMMIT;

-- ----------------------------
-- Table structure for cbf_jtcy
-- ----------------------------
DROP TABLE IF EXISTS "cbf_jtcy";
CREATE TABLE "cbf_jtcy" (
"cbfbm" char(18) COLLATE "default",
"cyxm" varchar(50) COLLATE "default",
"cyxb" char(1) COLLATE "default",
"cyzjlx" char(1) COLLATE "default",
"cyzjhm" varchar(20) COLLATE "default",
"yhzgx" char(2) COLLATE "default",
"cybz" char(1) COLLATE "default",
"sfgyr" char(1) COLLATE "default",
"id" int8 DEFAULT nextval('cbf_jtcy_id_seq'::regclass) NOT NULL,
"cymz" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbf_jtcy
-- ----------------------------
BEGIN;
INSERT INTO "cbf_jtcy" VALUES ('HB2313001CB001    ', '1', '2', '1', '65', '1 ', '1', '1', '1', '1');
INSERT INTO "cbf_jtcy" VALUES ('HB2313001CB002    ', '3', '1', '1', '77', '3 ', '3', '2', '100', '3');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '150', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '151', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '200', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '201', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '250', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '251', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '300', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '301', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '350', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '351', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '400', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '401', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '450', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '451', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '452', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '453', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '454', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '455', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '500', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '501', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '550', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '551', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '600', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '601', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '650', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '651', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '800', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '801', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '如孜.木拉', '1', '1', '652901195402123447', '弟弟', '9', '1', '850', '维族');
INSERT INTO "cbf_jtcy" VALUES ('11010100100101001 ', '吐尔逊.如孜', '1', '1', '652901195402123448', '儿子', '3', '1', '851', '维族');
COMMIT;

-- ----------------------------
-- Table structure for cbflxdmb
-- ----------------------------
DROP TABLE IF EXISTS "cbflxdmb";
CREATE TABLE "cbflxdmb" (
"dm" char(1) COLLATE "default" NOT NULL,
"cbflx" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbflxdmb
-- ----------------------------
BEGIN;
INSERT INTO "cbflxdmb" VALUES ('1', '农户');
INSERT INTO "cbflxdmb" VALUES ('2', '个人');
INSERT INTO "cbflxdmb" VALUES ('3', '单位');
COMMIT;

-- ----------------------------
-- Table structure for cbht
-- ----------------------------
DROP TABLE IF EXISTS "cbht";
CREATE TABLE "cbht" (
"cbhtbm" char(19) COLLATE "default" NOT NULL,
"ycbhtbm" char(19) COLLATE "default",
"fbfbm" char(14) COLLATE "default",
"cbfbm" char(18) COLLATE "default",
"cbfs" char(3) COLLATE "default",
"cbqxq" date,
"cbqxz" date,
"htzmj" float4,
"cbdkzs" int4,
"qdsj" date,
"id" int8 DEFAULT nextval('cbht_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbht
-- ----------------------------
BEGIN;
INSERT INTO "cbht" VALUES ('HB2313001CB00201   ', '0                  ', 'HB231300101002', 'HB2313001CB002    ', '1  ', '2014-10-02', '2014-10-30', '50', '2', '2014-10-25', '1', '303');
INSERT INTO "cbht" VALUES ('HB2313001CB00202   ', '0                  ', 'HB231300101001', 'HB2313001CB001    ', '4  ', '2014-10-08', '2014-10-10', '100', '3', '2014-10-25', '100', '303');
INSERT INTO "cbht" VALUES ('HB2313001CB00203   ', '0                  ', 'HB231300101001', 'HB2313001CB001    ', '   ', '2014-10-07', '2014-10-23', null, null, '2014-10-25', '150', '303');
INSERT INTO "cbht" VALUES ('11010100100101001J ', null, '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '200', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '250', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '251', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '252', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '300', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '350', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '400', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '450', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '500', null);
INSERT INTO "cbht" VALUES ('11010100100101001J ', '                   ', '11010100100101', '11010100100101001 ', '110', '2000-10-13', '2100-10-13', '100', '2', '2000-10-13', '550', null);
COMMIT;

-- ----------------------------
-- Table structure for cbjyqqdfsdmb
-- ----------------------------
DROP TABLE IF EXISTS "cbjyqqdfsdmb";
CREATE TABLE "cbjyqqdfsdmb" (
"dm" char(3) COLLATE "default" NOT NULL,
"qdfs" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbjyqqdfsdmb
-- ----------------------------
BEGIN;
INSERT INTO "cbjyqqdfsdmb" VALUES ('100', '承包');
INSERT INTO "cbjyqqdfsdmb" VALUES ('110', '家庭承包');
INSERT INTO "cbjyqqdfsdmb" VALUES ('120', '其他方式承包');
INSERT INTO "cbjyqqdfsdmb" VALUES ('121', '招标');
INSERT INTO "cbjyqqdfsdmb" VALUES ('122', '拍卖');
INSERT INTO "cbjyqqdfsdmb" VALUES ('123', '公开协商');
INSERT INTO "cbjyqqdfsdmb" VALUES ('129', '其他方式');
INSERT INTO "cbjyqqdfsdmb" VALUES ('200', '转让');
INSERT INTO "cbjyqqdfsdmb" VALUES ('300', '互换');
INSERT INTO "cbjyqqdfsdmb" VALUES ('900', '其他方式');
COMMIT;

-- ----------------------------
-- Table structure for cbjyqz
-- ----------------------------
DROP TABLE IF EXISTS "cbjyqz";
CREATE TABLE "cbjyqz" (
"cbjyqzbm" char(19) COLLATE "default",
"fzjg" char(50) COLLATE "default",
"fzrq" date,
"qzsfly" char(1) COLLATE "default",
"qzlqrq" date,
"qzlqrxm" char(10) COLLATE "default",
"qzlqrzjlx" char(1) COLLATE "default",
"qzlqrzjhm" char(20) COLLATE "default",
"id" int8 DEFAULT nextval('cbjyqz_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbjyqz
-- ----------------------------
BEGIN;
INSERT INTO "cbjyqz" VALUES ('HB2313001CB00201   ', '1                                                 ', '2015-02-02', '1', '2015-02-08', '112233    ', '4', '1                   ', '50', null);
INSERT INTO "cbjyqz" VALUES ('11010100100101001J ', '托普鲁克乡人民政府                                         ', '2014-01-01', '1', '2014-02-08', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '150', null);
INSERT INTO "cbjyqz" VALUES ('11010100100101001J ', '托普鲁克乡人民政府                                         ', '2014-01-01', '1', '2014-02-08', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '200', null);
INSERT INTO "cbjyqz" VALUES ('11010100100101001J ', '托普鲁克乡人民政府                                         ', '2014-01-01', '1', '2014-02-08', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '250', null);
INSERT INTO "cbjyqz" VALUES ('11010100100101001J ', '托普鲁克乡人民政府                                         ', '2014-01-01', '1', '2014-02-08', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '300', null);
INSERT INTO "cbjyqz" VALUES ('11010100100101001J ', '托普鲁克乡人民政府                                         ', '2014-01-01', '1', '2014-02-08', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '350', null);
INSERT INTO "cbjyqz" VALUES ('11010100100101001J ', '托普鲁克乡人民政府                                         ', '2014-01-01', '1', '2014-02-08', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '400', null);
COMMIT;

-- ----------------------------
-- Table structure for cbjyqz_qzbf
-- ----------------------------
DROP TABLE IF EXISTS "cbjyqz_qzbf";
CREATE TABLE "cbjyqz_qzbf" (
"cbjyqzbm" char(19) COLLATE "default",
"qzbfyy" char(200) COLLATE "default",
"bfrq" date,
"qzbflqrq" date,
"qzbflqrxm" char(10) COLLATE "default",
"bflqrzjlx" char(1) COLLATE "default",
"bflqrzjhm" char(20) COLLATE "default",
"id" int8 DEFAULT nextval('cbjyqz_qzbf_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbjyqz_qzbf
-- ----------------------------
BEGIN;
INSERT INTO "cbjyqz_qzbf" VALUES ('11010100100101001J ', '原有权证丢失                                                                                                                                                                                                  ', '2014-08-10', '2014-08-20', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '100', null);
INSERT INTO "cbjyqz_qzbf" VALUES ('11010100100101001J ', '原有权证丢失                                                                                                                                                                                                  ', '2014-08-10', '2014-08-20', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '150', null);
INSERT INTO "cbjyqz_qzbf" VALUES ('11010100100101001J ', '原有权证丢失                                                                                                                                                                                                  ', '2014-08-10', '2014-08-20', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '200', null);
INSERT INTO "cbjyqz_qzbf" VALUES ('11010100100101001J ', '原有权证丢失                                                                                                                                                                                                  ', '2014-08-10', '2014-08-20', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '250', null);
INSERT INTO "cbjyqz_qzbf" VALUES ('11010100100101001J ', '原有权证丢失                                                                                                                                                                                                  ', '2014-08-10', '2014-08-20', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '300', null);
COMMIT;

-- ----------------------------
-- Table structure for cbjyqz_qzhf
-- ----------------------------
DROP TABLE IF EXISTS "cbjyqz_qzhf";
CREATE TABLE "cbjyqz_qzhf" (
"cbjyqzbm" char(19) COLLATE "default",
"qzhfyy" char(200) COLLATE "default",
"hfrq" date,
"qzhflqrq" date,
"qzhflqrxm" char(10) COLLATE "default",
"hflqrzjlx" char(1) COLLATE "default",
"hflqrzjhm" char(20) COLLATE "default",
"id" int8 DEFAULT nextval('cbjyqz_qzhf_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbjyqz_qzhf
-- ----------------------------
BEGIN;
INSERT INTO "cbjyqz_qzhf" VALUES ('11010100100101001J ', '权证旧换新                                                                                                                                                                                                   ', '2014-09-01', '2014-10-01', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '100', null);
INSERT INTO "cbjyqz_qzhf" VALUES ('11010100100101001J ', '权证旧换新                                                                                                                                                                                                   ', '2014-09-01', '2014-10-01', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '150', null);
INSERT INTO "cbjyqz_qzhf" VALUES ('11010100100101001J ', '权证旧换新                                                                                                                                                                                                   ', '2014-09-01', '2014-10-01', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '200', null);
INSERT INTO "cbjyqz_qzhf" VALUES ('11010100100101001J ', '权证旧换新                                                                                                                                                                                                   ', '2014-09-01', '2014-10-01', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '250', null);
INSERT INTO "cbjyqz_qzhf" VALUES ('11010100100101001J ', '权证旧换新                                                                                                                                                                                                   ', '2014-09-01', '2014-10-01', '吐尔汗·莫拉    ', '1', '652901195402123427  ', '300', null);
COMMIT;

-- ----------------------------
-- Table structure for cbjyqz_qzzx
-- ----------------------------
DROP TABLE IF EXISTS "cbjyqz_qzzx";
CREATE TABLE "cbjyqz_qzzx" (
"cbjyqzbm" char(19) COLLATE "default",
"zxyy" char(200) COLLATE "default",
"zxrq" date,
"id" int8 DEFAULT nextval('cbjyqz_qzzx_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbjyqz_qzzx
-- ----------------------------
BEGIN;
INSERT INTO "cbjyqz_qzzx" VALUES ('11010100100101001J ', '搬家到城市                                                                                                                                                                                                   ', '2014-10-02', '300', null);
COMMIT;

-- ----------------------------
-- Table structure for cbjyqzdjb
-- ----------------------------
DROP TABLE IF EXISTS "cbjyqzdjb";
CREATE TABLE "cbjyqzdjb" (
"cbjyqzbm" char(19) COLLATE "default",
"fbfbm" char(14) COLLATE "default",
"cbfbm" char(18) COLLATE "default",
"cbfs" char(3) COLLATE "default",
"cbqx" char(30) COLLATE "default",
"cbqxq" date,
"cbqxz" date,
"dksyt" varchar(255) COLLATE "default",
"id" int8 DEFAULT nextval('cbjyqzdjb_id_seq'::regclass) NOT NULL,
"org_id" int8,
"date" date
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cbjyqzdjb
-- ----------------------------
BEGIN;
INSERT INTO "cbjyqzdjb" VALUES ('HB2313001CB00202   ', 'HB231300101001', 'HB2313001CB001    ', '4  ', '1                             ', '2014-10-08', '2014-10-10', '', '1', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('HB2313001CB00202   ', 'HB231300101001', 'HB2313001CB001    ', '4  ', '1                             ', '2014-10-08', '2014-10-10', '', '200', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('HB2313001CB00201   ', 'HB231300101002', 'HB2313001CB002    ', '1  ', '1                             ', '2014-10-02', '2014-10-30', '', '250', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('11010100100101001J ', '11010100100101', '11010100100101001 ', '110', '100年                          ', '2000-10-13', '2100-10-13', 'image/11010100100101001J0001.jpg,image/11010100100101001J0002.jgp', '300', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('11010100100101001J ', '11010100100101', '11010100100101001 ', '110', '100年                          ', '2000-10-13', '2100-10-13', 'image/11010100100101001J0001.jpg,image/11010100100101001J0002.jgp', '350', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('11010100100101001J ', '11010100100101', '11010100100101001 ', '110', '100年                          ', '2000-10-13', '2100-10-13', 'image/11010100100101001J0001.jpg,image/11010100100101001J0002.jgp', '400', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('11010100100101001J ', '11010100100101', '11010100100101001 ', '110', '100年                          ', '2000-10-13', '2100-10-13', 'image/11010100100101001J0001.jpg,image/11010100100101001J0002.jgp', '500', null, null);
INSERT INTO "cbjyqzdjb" VALUES ('11010100100101001J ', '11010100100101', '11010100100101001 ', '110', '100年                          ', '2000-10-13', '2100-10-13', 'image/11010100100101001J0001.jpg,image/11010100100101001J0002.jgp', '550', null, null);
COMMIT;

-- ----------------------------
-- Table structure for cybzdmb
-- ----------------------------
DROP TABLE IF EXISTS "cybzdmb";
CREATE TABLE "cybzdmb" (
"dm" char(1) COLLATE "default" NOT NULL,
"cybz" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cybzdmb
-- ----------------------------
BEGIN;
INSERT INTO "cybzdmb" VALUES ('1', '外嫁女');
INSERT INTO "cybzdmb" VALUES ('2', '入赘男');
INSERT INTO "cybzdmb" VALUES ('3', '在校大学生');
INSERT INTO "cybzdmb" VALUES ('4', '公家公职人员');
INSERT INTO "cybzdmb" VALUES ('5', '军人（军官、士兵）');
INSERT INTO "cybzdmb" VALUES ('6', '新生儿');
INSERT INTO "cybzdmb" VALUES ('7', '去世');
INSERT INTO "cybzdmb" VALUES ('9', '其他备注');
COMMIT;

-- ----------------------------
-- Table structure for dk
-- ----------------------------
DROP TABLE IF EXISTS "dk";
CREATE TABLE "dk" (
"id" int8 DEFAULT nextval('dk_id_seq'::regclass) NOT NULL,
"dkbm" char(22) COLLATE "default",
"dkmc" varchar(50) COLLATE "default",
"syqxz" char(2) COLLATE "default",
"dklb" char(2) COLLATE "default",
"tdlylx" char(3) COLLATE "default",
"dldj" char(2) COLLATE "default",
"tdyt" char(1) COLLATE "default",
"sfjbnt" char(1) COLLATE "default",
"scmj" float4,
"dkdz" varchar(50) COLLATE "default",
"dkxz" varchar(50) COLLATE "default",
"dknz" varchar(50) COLLATE "default",
"dkbz" varchar(50) COLLATE "default",
"dkbzxx" varchar(300) COLLATE "default",
"zjrxm" varchar(100) COLLATE "default",
"org_id" int8,
"cbht_id" int8 DEFAULT 0
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of dk
-- ----------------------------
BEGIN;
INSERT INTO "dk" VALUES ('50', '652901202200S01       ', '三岔口01', '10', '10', '013', '01', '1', '1', '100', '1', '2', '3', '4', '5', '6', '303', '100');
INSERT INTO "dk" VALUES ('51', '652901202200S02       ', '三岔口02', '31', '21', '02 ', '02', '1', '1', '50', '4', '3', '2', '1', '1', '1', '303', '151');
INSERT INTO "dk" VALUES ('100', '652901202200S03       ', '三叉口03', '10', '10', '02 ', '06', '1', '1', '102', '1', '2', '3', '4', '5', '6', '303', '150');
INSERT INTO "dk" VALUES ('150', '652901202200S04       ', '三叉口04', '30', '21', '012', '02', '2', '1', '100', '上桥东100米', '上桥西100米', '上桥南100米', '上桥北100米', '地块界限完好', '张钦', '303', '0');
INSERT INTO "dk" VALUES ('250', '11010100100101001J0001', '地块1', '31', '10', '011', '01', '1', '1', '40.5', '1.2,1.3', '2.2,2.3', '3.2,3.3', '4.2,4.3', '地块备注测试信息', '指界人1', null, '1');
INSERT INTO "dk" VALUES ('251', '11010100100101001J0002', '地块2', '31', '10', '011', '01', '1', '1', '59.5', '1.2,1.4', '2.2,2.4', '3.2,3.4', '4.2,4.4', '地块备注测试信息', '指界人2', null, '1');
INSERT INTO "dk" VALUES ('300', '11010100100101001J0001', '地块1', '31', '10', '011', '01', '1', '1', '40.5', '1.2,1.3', '2.2,2.3', '3.2,3.3', '4.2,4.3', '地块备注测试信息', '指界人1', null, null);
INSERT INTO "dk" VALUES ('301', '11010100100101001J0002', '地块2', '31', '10', '011', '01', '1', '1', '59.5', '1.2,1.4', '2.2,2.4', '3.2,3.4', '4.2,4.4', '地块备注测试信息', '指界人2', null, null);
INSERT INTO "dk" VALUES ('350', '11010100100101001J0001', '地块1', '31', '10', '011', '01', '1', '1', '40.5', '1.2,1.3', '2.2,2.3', '3.2,3.3', '4.2,4.3', '地块备注测试信息', '指界人1', null, null);
INSERT INTO "dk" VALUES ('351', '11010100100101001J0002', '地块2', '31', '10', '011', '01', '1', '1', '59.5', '1.2,1.4', '2.2,2.4', '3.2,3.4', '4.2,4.4', '地块备注测试信息', '指界人2', null, null);
INSERT INTO "dk" VALUES ('400', '11010100100101001J0001', '地块1', '31', '10', '011', '01', '1', '1', '40.5', '1.2,1.3', '2.2,2.3', '3.2,3.3', '4.2,4.3', '地块备注测试信息', '指界人1', null, null);
INSERT INTO "dk" VALUES ('401', '11010100100101001J0002', '地块2', '31', '10', '011', '01', '1', '1', '59.5', '1.2,1.4', '2.2,2.4', '3.2,3.4', '4.2,4.4', '地块备注测试信息', '指界人2', null, null);
INSERT INTO "dk" VALUES ('450', '11010100100101001J0001', '地块1', '31', '10', '011', '01', '1', '1', '40.5', '1.2,1.3', '2.2,2.3', '3.2,3.3', '4.2,4.3', '地块备注测试信息', '指界人1', null, null);
INSERT INTO "dk" VALUES ('451', '11010100100101001J0002', '地块2', '31', '10', '011', '01', '1', '1', '59.5', '1.2,1.4', '2.2,2.4', '3.2,3.4', '4.2,4.4', '地块备注测试信息', '指界人2', null, null);
INSERT INTO "dk" VALUES ('500', '11010100100101001J0001', '地块1', '31', '10', '011', '01', '1', '1', '40.5', '1.2,1.3', '2.2,2.3', '3.2,3.3', '4.2,4.3', '地块备注测试信息', '指界人1', null, null);
INSERT INTO "dk" VALUES ('501', '11010100100101001J0002', '地块2', '31', '10', '011', '01', '1', '1', '59.5', '1.2,1.4', '2.2,2.4', '3.2,3.4', '4.2,4.4', '地块备注测试信息', '指界人2', null, null);
COMMIT;

-- ----------------------------
-- Table structure for dklbdmb
-- ----------------------------
DROP TABLE IF EXISTS "dklbdmb";
CREATE TABLE "dklbdmb" (
"dm" char(2) COLLATE "default" NOT NULL,
"dkxz" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of dklbdmb
-- ----------------------------
BEGIN;
INSERT INTO "dklbdmb" VALUES ('10', '承包地块');
INSERT INTO "dklbdmb" VALUES ('21', '自留地');
INSERT INTO "dklbdmb" VALUES ('22', '机动地');
INSERT INTO "dklbdmb" VALUES ('23', '开荒地');
INSERT INTO "dklbdmb" VALUES ('99', '其他集体土地');
COMMIT;

-- ----------------------------
-- Table structure for dldjdmb
-- ----------------------------
DROP TABLE IF EXISTS "dldjdmb";
CREATE TABLE "dldjdmb" (
"dm" char(2) COLLATE "default" NOT NULL,
"dldj" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of dldjdmb
-- ----------------------------
BEGIN;
INSERT INTO "dldjdmb" VALUES ('01', '一等地');
INSERT INTO "dldjdmb" VALUES ('02', '二等地');
INSERT INTO "dldjdmb" VALUES ('03', '三等地');
INSERT INTO "dldjdmb" VALUES ('04', '四等地');
INSERT INTO "dldjdmb" VALUES ('05', '五等地');
INSERT INTO "dldjdmb" VALUES ('06', '六等地');
INSERT INTO "dldjdmb" VALUES ('07', '七等地');
INSERT INTO "dldjdmb" VALUES ('08', '八等地');
INSERT INTO "dldjdmb" VALUES ('09', '九等地');
INSERT INTO "dldjdmb" VALUES ('10', '十等地');
COMMIT;

-- ----------------------------
-- Table structure for fbf
-- ----------------------------
DROP TABLE IF EXISTS "fbf";
CREATE TABLE "fbf" (
"fbfbm" char(14) COLLATE "default",
"fbfmc" varchar(50) COLLATE "default",
"fbffzrxm" varchar(50) COLLATE "default",
"fzrzjlx" char(1) COLLATE "default",
"fzrzjhm" varchar(30) COLLATE "default",
"lxdh" varchar(15) COLLATE "default",
"fbfdz" varchar(100) COLLATE "default",
"yzbm" char(6) COLLATE "default",
"fbfdcy" varchar(300) COLLATE "default",
"fbfdcrq" timestamp(6),
"fbfdcjs" varchar(300) COLLATE "default",
"id" int8 DEFAULT nextval('fbf_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of fbf
-- ----------------------------
BEGIN;
INSERT INTO "fbf" VALUES ('HB231300101001', '南马村支书', '魏国强', '1', '3685964525845', '223456', '河北省高阳县浦口乡南马村', '201445', '魏宗人', '2014-09-08 00:00:00', '土地合格', '153', '252');
INSERT INTO "fbf" VALUES ('HB231300101002', '南马村支书', '魏国强', '1', '3685964525845', '123456', '河北省高阳县浦口乡南马村', '201445', '魏宗人', '2014-09-08 00:00:00', '土地合格', '156', '252');
INSERT INTO "fbf" VALUES ('11010100100101', '尤喀克喀拉喀勒村', '阿布拉·艾赛提', '1', '652901195402124252', '110011', '托普鲁克乡尤克喀拉喀勒村', '843003', '调查员1', '2014-10-13 00:00:00', '合格', '900', '0');
INSERT INTO "fbf" VALUES ('11010100100101', '尤喀克喀拉喀勒村', '阿布拉·艾赛提', '1', '652901195402124252', '110011', '托普鲁克乡尤克喀拉喀勒村', '843003', '调查员1', '2014-10-13 00:00:00', '合格', '950', '0');
INSERT INTO "fbf" VALUES ('11010100100101', '尤喀克喀拉喀勒村', '阿布拉·艾赛提', '1', '652901195402124252', '110011', '托普鲁克乡尤克喀拉喀勒村', '843003', '调查员1', '2014-10-13 00:00:00', '合格', '1000', '0');
INSERT INTO "fbf" VALUES ('11010100100101', '尤喀克喀拉喀勒村', '阿布拉·艾赛提', '1', '652901195402124252', '110011', '托普鲁克乡尤克喀拉喀勒村', '843003', '调查员1', '2014-10-13 00:00:00', '合格', '1050', '0');
INSERT INTO "fbf" VALUES ('11010100100101', '尤喀克喀拉喀勒村', '阿布拉·艾赛提', '1', '652901195402124252', '110011', '托普鲁克乡尤克喀拉喀勒村', '843003', '调查员1', '2014-10-13 00:00:00', '合格', '1051', '0');
INSERT INTO "fbf" VALUES ('11010100100101', '尤喀克喀拉喀勒村', '阿布拉·艾赛提', '1', '652901195402124252', '110011', '托普鲁克乡尤克喀拉喀勒村', '843003', '调查员1', '2014-10-13 00:00:00', '合格', '1100', '0');
COMMIT;

-- ----------------------------
-- Table structure for lzht
-- ----------------------------
DROP TABLE IF EXISTS "lzht";
CREATE TABLE "lzht" (
"ycbhtbm" char(19) COLLATE "default",
"lzhtbm" char(18) COLLATE "default",
"cbfbm" char(18) COLLATE "default",
"srfbm" char(18) COLLATE "default",
"lzfs" char(3) COLLATE "default",
"lzqx" char(10) COLLATE "default",
"lzqxksrq" date,
"lzqxjsrq" date,
"lzmj" float4,
"lzdks" int4,
"lzqtdyt" char(1) COLLATE "default",
"lzhtdyt" char(1) COLLATE "default",
"lzjgsm" char(100) COLLATE "default",
"htqdrq" date,
"id" int8 DEFAULT nextval('lzht_id_seq'::regclass) NOT NULL,
"org_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lzht
-- ----------------------------
BEGIN;
INSERT INTO "lzht" VALUES ('23                 ', '12                ', '12                ', '12                ', '12 ', '12        ', '2014-11-12', '2014-11-12', '12', '12', '1', '1', '12                                                                                                  ', '2014-11-12', '100', '350');
INSERT INTO "lzht" VALUES ('3423452            ', '12                ', '12                ', '12                ', '123', '12        ', '2014-11-12', '2014-11-12', '12', '12', '1', '1', '12                                                                                                  ', '2014-11-12', '101', '350');
INSERT INTO "lzht" VALUES ('13                 ', '13                ', '13                ', '13                ', '13 ', '13        ', '2014-11-13', '2014-11-13', '13', '13', '1', '1', '13                                                                                                  ', '2014-11-13', '102', '303');
INSERT INTO "lzht" VALUES (null, '11010100100101101 ', '11010100100101001 ', '11010100100101002 ', '200', '1年        ', '2014-10-01', '2015-10-01', '40.5', '1', '1', '1', '10000元/年                                                                                            ', '2014-10-01', '250', null);
INSERT INTO "lzht" VALUES (null, '11010100100101101 ', '11010100100101001 ', '11010100100101002 ', '200', '1年        ', '2014-10-01', '2015-10-01', '40.5', '1', '1', '1', '10000元/年                                                                                            ', '2014-10-01', '300', null);
INSERT INTO "lzht" VALUES (null, '11010100100101101 ', '11010100100101001 ', '11010100100101002 ', '200', '1年        ', '2014-10-01', '2015-10-01', '40.5', '1', '1', '1', '10000元/年                                                                                            ', '2014-10-01', '350', null);
INSERT INTO "lzht" VALUES ('11010100100101001J ', '11010100100101101 ', '11010100100101001 ', '11010100100101002 ', '200', '1年        ', '2014-10-01', '2015-10-01', '40.5', '1', '1', '1', '10000元/年                                                                                            ', '2014-10-01', '400', null);
INSERT INTO "lzht" VALUES ('11010100100101001J ', '11010100100101101 ', '11010100100101001 ', '11010100100101002 ', '200', '1年        ', '2014-10-01', '2015-10-01', '40.5', '1', '1', '1', '10000元/年                                                                                            ', '2014-10-01', '450', null);
INSERT INTO "lzht" VALUES ('11010100100101001J ', '11010100100101101 ', '11010100100101001 ', '11010100100101002 ', '200', '1年        ', '2014-10-01', '2015-10-01', '40.5', '1', '1', '1', '10000元/年                                                                                            ', '2014-10-01', '500', null);
COMMIT;

-- ----------------------------
-- Table structure for qslyzlfj
-- ----------------------------
DROP TABLE IF EXISTS "qslyzlfj";
CREATE TABLE "qslyzlfj" (
"cbjyqzbm" char(19) COLLATE "default" DEFAULT nextval('qslyzlfj_id_seq'::regclass),
"zlfjbh" char(20) COLLATE "default",
"zlfjmc" char(100) COLLATE "default",
"zlfjrq" date,
"fj" varchar(255) COLLATE "default",
"id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "qslyzlfj"."cbjyqzbm" IS 'qslyzlfj_id_seq';

-- ----------------------------
-- Records of qslyzlfj
-- ----------------------------
BEGIN;
INSERT INTO "qslyzlfj" VALUES ('11010100100101001J ', '11010100100101001   ', '土地承包证书                                                                                              ', '2014-10-02', 'qsly/11010100100101001.doc', '0');
INSERT INTO "qslyzlfj" VALUES ('11010100100101001J ', '11010100100101001   ', '土地承包证书                                                                                              ', '2014-10-02', 'qsly/11010100100101001.doc', '1');
INSERT INTO "qslyzlfj" VALUES ('11010100100101001J ', '11010100100101001   ', '土地承包证书                                                                                              ', '2014-10-02', 'qsly/11010100100101001.doc', '100');
INSERT INTO "qslyzlfj" VALUES ('11010100100101001J ', '11010100100101001   ', '土地承包证书                                                                                              ', '2014-10-02', 'qsly/11010100100101001.doc', '150');
INSERT INTO "qslyzlfj" VALUES ('11010100100101001J ', '11010100100101001   ', '土地承包证书                                                                                              ', '2014-10-02', 'qsly/11010100100101001.doc', '200');
INSERT INTO "qslyzlfj" VALUES ('11010100100101001J ', '11010100100101001   ', '土地承包证书                                                                                              ', '2014-10-02', 'qsly/11010100100101001.doc', '250');
COMMIT;

-- ----------------------------
-- Table structure for sfdmb
-- ----------------------------
DROP TABLE IF EXISTS "sfdmb";
CREATE TABLE "sfdmb" (
"dm" char(1) COLLATE "default" NOT NULL,
"sf" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sfdmb
-- ----------------------------
BEGIN;
INSERT INTO "sfdmb" VALUES ('1', '是');
INSERT INTO "sfdmb" VALUES ('2', '否');
COMMIT;

-- ----------------------------
-- Table structure for syqsxdmb
-- ----------------------------
DROP TABLE IF EXISTS "syqsxdmb";
CREATE TABLE "syqsxdmb" (
"dm" char(2) COLLATE "default" NOT NULL,
"syqsx" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of syqsxdmb
-- ----------------------------
BEGIN;
INSERT INTO "syqsxdmb" VALUES ('10', '国有土地所有权');
INSERT INTO "syqsxdmb" VALUES ('30', '集体土地所有权');
INSERT INTO "syqsxdmb" VALUES ('31', '村民小组');
INSERT INTO "syqsxdmb" VALUES ('32', '村级集体经济组织');
INSERT INTO "syqsxdmb" VALUES ('33', '乡级集体经济组织');
INSERT INTO "syqsxdmb" VALUES ('34', '其他农民集体经济组织');
COMMIT;

-- ----------------------------
-- Table structure for tdlylx
-- ----------------------------
DROP TABLE IF EXISTS "tdlylx";
CREATE TABLE "tdlylx" (
"id" int4 DEFAULT nextval('tdlylx_id_seq'::regclass) NOT NULL,
"lbbm" char(3) COLLATE "default",
"lbmc" varchar(64) COLLATE "default",
"hy" varchar(1024) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tdlylx
-- ----------------------------
BEGIN;
INSERT INTO "tdlylx" VALUES ('1', '01 ', '耕地', null);
INSERT INTO "tdlylx" VALUES ('2', '011', '水田', null);
INSERT INTO "tdlylx" VALUES ('3', '012', '水浇地', null);
INSERT INTO "tdlylx" VALUES ('4', '013', '旱地', null);
INSERT INTO "tdlylx" VALUES ('5', '02 ', '园地', null);
INSERT INTO "tdlylx" VALUES ('6', '021', '果园', null);
INSERT INTO "tdlylx" VALUES ('7', '022', '茶园', null);
INSERT INTO "tdlylx" VALUES ('8', '023', '其他园地', null);
INSERT INTO "tdlylx" VALUES ('9', '03 ', '林地', null);
INSERT INTO "tdlylx" VALUES ('10', '031', '有林地', null);
INSERT INTO "tdlylx" VALUES ('11', '032', '灌木林地', null);
INSERT INTO "tdlylx" VALUES ('12', '033', '其他林地', null);
INSERT INTO "tdlylx" VALUES ('13', '04 ', '草地', null);
INSERT INTO "tdlylx" VALUES ('14', '041', '天然牧草地', null);
INSERT INTO "tdlylx" VALUES ('15', '042', '人工牧草地', null);
INSERT INTO "tdlylx" VALUES ('16', '043', '其他草地', null);
INSERT INTO "tdlylx" VALUES ('17', '05 ', '商服用地', null);
INSERT INTO "tdlylx" VALUES ('18', '051', '批发零售用地', null);
COMMIT;

-- ----------------------------
-- Table structure for tdytdmb
-- ----------------------------
DROP TABLE IF EXISTS "tdytdmb";
CREATE TABLE "tdytdmb" (
"dm" char(1) COLLATE "default" NOT NULL,
"tdytd" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tdytdmb
-- ----------------------------
BEGIN;
INSERT INTO "tdytdmb" VALUES ('1', '种植业');
INSERT INTO "tdytdmb" VALUES ('2', '林业');
INSERT INTO "tdytdmb" VALUES ('3', '畜牧业');
INSERT INTO "tdytdmb" VALUES ('4', '渔业');
INSERT INTO "tdytdmb" VALUES ('5', '非农业用途');
COMMIT;

-- ----------------------------
-- Table structure for xbdmb
-- ----------------------------
DROP TABLE IF EXISTS "xbdmb";
CREATE TABLE "xbdmb" (
"dm" char(1) COLLATE "default" NOT NULL,
"xb" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of xbdmb
-- ----------------------------
BEGIN;
INSERT INTO "xbdmb" VALUES ('1', '男');
INSERT INTO "xbdmb" VALUES ('2', '女');
COMMIT;

-- ----------------------------
-- Table structure for zjlxdmb
-- ----------------------------
DROP TABLE IF EXISTS "zjlxdmb";
CREATE TABLE "zjlxdmb" (
"dm" char(1) COLLATE "default" NOT NULL,
"zjlx" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of zjlxdmb
-- ----------------------------
BEGIN;
INSERT INTO "zjlxdmb" VALUES ('1', '居民身份证');
INSERT INTO "zjlxdmb" VALUES ('2', '军官证');
INSERT INTO "zjlxdmb" VALUES ('3', '行政、企事业单位机构代码证或法人代码证');
INSERT INTO "zjlxdmb" VALUES ('4', '户口薄');
INSERT INTO "zjlxdmb" VALUES ('5', '护照');
INSERT INTO "zjlxdmb" VALUES ('9', '其他证件');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table a_organization
-- ----------------------------
ALTER TABLE "a_organization" ADD PRIMARY KEY ("org_id");

-- ----------------------------
-- Primary Key structure for table a_user
-- ----------------------------
ALTER TABLE "a_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbdkxx
-- ----------------------------
ALTER TABLE "cbdkxx" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbf
-- ----------------------------
ALTER TABLE "cbf" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbf_jtcy
-- ----------------------------
ALTER TABLE "cbf_jtcy" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbflxdmb
-- ----------------------------
ALTER TABLE "cbflxdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table cbht
-- ----------------------------
ALTER TABLE "cbht" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbjyqqdfsdmb
-- ----------------------------
ALTER TABLE "cbjyqqdfsdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table cbjyqz
-- ----------------------------
ALTER TABLE "cbjyqz" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbjyqz_qzbf
-- ----------------------------
ALTER TABLE "cbjyqz_qzbf" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbjyqz_qzhf
-- ----------------------------
ALTER TABLE "cbjyqz_qzhf" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbjyqz_qzzx
-- ----------------------------
ALTER TABLE "cbjyqz_qzzx" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cbjyqzdjb
-- ----------------------------
ALTER TABLE "cbjyqzdjb" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cybzdmb
-- ----------------------------
ALTER TABLE "cybzdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table dk
-- ----------------------------
ALTER TABLE "dk" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table dklbdmb
-- ----------------------------
ALTER TABLE "dklbdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table dldjdmb
-- ----------------------------
ALTER TABLE "dldjdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table fbf
-- ----------------------------
ALTER TABLE "fbf" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lzht
-- ----------------------------
ALTER TABLE "lzht" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table qslyzlfj
-- ----------------------------
ALTER TABLE "qslyzlfj" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sfdmb
-- ----------------------------
ALTER TABLE "sfdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table syqsxdmb
-- ----------------------------
ALTER TABLE "syqsxdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table tdlylx
-- ----------------------------
ALTER TABLE "tdlylx" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tdytdmb
-- ----------------------------
ALTER TABLE "tdytdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table xbdmb
-- ----------------------------
ALTER TABLE "xbdmb" ADD PRIMARY KEY ("dm");

-- ----------------------------
-- Primary Key structure for table zjlxdmb
-- ----------------------------
ALTER TABLE "zjlxdmb" ADD PRIMARY KEY ("dm");
