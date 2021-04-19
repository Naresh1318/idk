# Copyright (C) 2019  Verkada

DESCRIPTION = "runit init system"
HOMEPAGE = "http://smarden.org/runit/"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package/COPYING;md5=c9e8a560732fc8b860b6a91341cc603b"

SRC_URI = "http://smarden.org/runit/${PN}-${PV}.tar.gz;name=srcs"

SRC_URI[srcs.md5sum] = "6c985fbfe3a34608eb3c53dc719172c4"
SRC_URI[srcs.sha256sum] = "6fd0160cb0cf1207de4e66754b6d39750cff14bb0aa66ab49490992c0c47ba18"

S = "${WORKDIR}/admin/${PN}-${PV}"

EXTRA_OEMAKE_append = " -C src 'CC=${CC}' 'LD=${CC}' 'AR=${AR}' 'RANLIB=${RANLIB}' 'LDFLAGS=${LDFLAGS}'"

PARALLEL_MAKE = ""

do_compile() {
    touch ${S}/src/systype
    oe_runmake
}

do_install() {
    install -d ${D}${base_sbindir}
    for c in $(cat ${S}/package/commands)
    do
        install -m 0755 \
                ${S}/src/$c \
                ${D}${base_sbindir}/
    done

    ln -sf ${base_sbindir}/runit ${D}${base_sbindir}/init
}

INSANE_SKIP_${PN} += "already-stripped"
