SUMMARY = "Package for ${IMAGE_NAME}"
# This license statement is a lie. Ideally set it to something more appropriate.
LICENSE = "CLOSED"

INHIBIT_DEFAULT_DEPS = "1"

inherit bin_package

# Where to install the image
vesseldir ?= "${localstatedir}/lib/libvirt/images"

do_install[depends] += "libvirt:do_install"

S = "${WORKDIR}/${BP}"

do_install () {
	install -d ${D}${vesseldir}
	install ${UNPACKDIR}/${VESSEL_PAYLOAD_NAME} ${D}${vesseldir}/${VESSEL_PAYLOAD_NAME}
}
