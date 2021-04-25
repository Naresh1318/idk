SUMMARY = "Hello with Makefile"
DESCRIPTION = "A test application to demonstrate how to create a recipe for makefile-based project."

SECTION = "examples"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/Naresh1318/poky-cpp-hello-world.git;protocol=https"
SRCREV = "0ccd9b384c4f476783fba61ee7da46db2fe1df66"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CXX=${CXX}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CXXFLAGS=${CXXFLAGS} -I${S}/. -DWITHOUT_XATTR' 'BUILDDIR=${S}'"

do_install () {
    oe_runmake install DESTDIR=${D} BINDIR=${bindir} SBINDIR=${sbindir} \
        MANDIR=${mandir} INCLUDEDIR=${includedir}
}
